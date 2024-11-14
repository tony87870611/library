package com.service.impl;

import com.entity.BookBorrowEntity;
import com.entity.BookInfoEntity;
import com.mapper.BookBorrowFlowMapper;
import com.mapper.BookInfoMapper;
import com.service.ReturnService;
import com.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.enums.BookStatus.AVAILABLE;
import static com.enums.BookStatus.RENTAL;
import static com.enums.ErrorCode.*;
import static com.enums.ErrorCode.UPDATE_BOOK_ERROR;

@Service
public class ReturnServiceImpl implements ReturnService {
    private static final String BOOK_LOCK_KEY_PREFIX = "BOOK_LOCK_";

    @Resource
    private BookBorrowFlowMapper bookBorrowFlowMapper;

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Transactional
    public void returnBook(String bookId) throws ValidationException {

        String lockKey = BOOK_LOCK_KEY_PREFIX + bookId;
        String lockValue = UUID.randomUUID().toString();

        try {
            //嘗試獲取分布式鎖
            if (!acquireLock(lockKey, lockValue)) {
                throw new ValidationException(BOOK_LOCK_ERROR.getCode(), BOOK_LOCK_ERROR.getMessage());
            }
            performReturning(bookId);
        } finally {
            //釋放鎖
            releaseLock(lockKey, lockValue);
        }
    }

    private void performReturning(String bookId) throws ValidationException {
        //查詢書籍訊息
        BookInfoEntity bookInfoEntity = bookInfoMapper.selectByBookId(bookId);
        if (bookInfoEntity == null) {
            throw new ValidationException(BOOK_NOT_FOUND.getCode(), BOOK_NOT_FOUND.getMessage());
        }

        //檢查書籍是否可還
        if (!isBookAvailable(bookInfoEntity)) {
            throw new ValidationException(BOOK_NOT_AVAILABLE.getCode(), BOOK_NOT_AVAILABLE.getMessage());
        }
        //插入一筆還書流水
        BookBorrowEntity bookBorrowEntity = buildBookBorrowEntity(bookId, RENTAL.getCode());
        int count1 = bookBorrowFlowMapper.insertSelective(bookBorrowEntity);
        if (count1 != 1) {
            throw new ValidationException(CREATE_BOOK_FLOW_ERROR.getCode(), CREATE_BOOK_FLOW_ERROR.getMessage());
        }
        //更新書籍狀態從租借中到可借書
        int count2 = bookInfoMapper.updateByStatusAndBookId(bookId, RENTAL.getCode(), AVAILABLE.getCode());
        if (count2 != 1) {
            throw new ValidationException(UPDATE_BOOK_ERROR.getCode(), UPDATE_BOOK_ERROR.getMessage());
        }
    }

    /**
     * 嘗試獲取分布式鎖
     *
     * @param lockKey   鎖的鍵
     * @param lockValue 鎖的值
     * @return
     */
    private boolean acquireLock(String lockKey, String lockValue) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 5, TimeUnit.SECONDS);
    }

    /**
     * 釋放分布式鎖
     *
     * @param lockKey   鎖的鍵
     * @param lockValue 鎖的值
     */
    private void releaseLock(String lockKey, String lockValue) {
        if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))) {
            redisTemplate.delete(lockKey);
        }
    }

    /**
     * 檢查書籍是否可還
     * @param bookInfoEntity
     * @return
     */
    private boolean isBookAvailable(BookInfoEntity bookInfoEntity) {
        return Objects.equals(bookInfoEntity.getStatus(), RENTAL.getCode());
    }

    /**
     * 創建借書流水實體
     * @param bookId
     * @param currentStatus
     * @return
     */
    private BookBorrowEntity buildBookBorrowEntity(String bookId, Integer currentStatus) {
        BookBorrowEntity bookBorrowEntity = new BookBorrowEntity();
        bookBorrowEntity.setBookId(bookId);
        bookBorrowEntity.setSerialNo(UUID.randomUUID().toString().substring(0, 16));
        bookBorrowEntity.setStatus(currentStatus);
        return bookBorrowEntity;
    }
}
