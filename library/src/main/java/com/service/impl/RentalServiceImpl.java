package com.service.impl;

import com.entity.BookBorrowEntity;
import com.entity.BookInfoEntity;
import com.mapper.BookBorrowFlowMapper;
import com.mapper.BookInfoMapper;
import com.service.RentalService;
import com.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.enums.ErrorCode.*;
import static com.enums.RentalStatus.AVAILABLE;
import static com.enums.RentalStatus.RENTAL;

@Service
public class RentalServiceImpl implements RentalService {

    private static final String BOOK_LOCK_KEY_PREFIX = "BOOK_LOCK_";

    @Resource
    private BookBorrowFlowMapper bookBorrowFlowMapper;

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Transactional
    public void borrowBook(String bookId) throws ValidationException {

        String lockKey = BOOK_LOCK_KEY_PREFIX + bookId;
        String lockValue = UUID.randomUUID().toString();

        try {
            //嘗試獲取分布式鎖
            if (!acquireLock(lockKey, lockValue)) {
                throw new ValidationException(BOOK_LOCK_ERROR.getCode(), BOOK_LOCK_ERROR.getMessage());
            }
            performBorrowing(bookId);
        } finally {
            //釋放鎖
            releaseLock(lockKey, lockValue);
        }
    }

    private void performBorrowing(String bookId) throws ValidationException {
        //查詢書籍訊息
        BookInfoEntity bookInfoEntity = bookInfoMapper.selectByBookId(bookId);
        if (bookInfoEntity == null) {
            throw new ValidationException(BOOK_NOT_FOUND.getCode(), BOOK_NOT_FOUND.getMessage());
        }

        //檢查書籍是否可還
        if (!isBookAvailable(bookInfoEntity)) {
            throw new ValidationException(BOOK_NOT_AVAILABLE.getCode(), BOOK_NOT_AVAILABLE.getMessage());
        }
        BookBorrowEntity bookBorrowEntity = buildBookBorrowEntity(bookId, bookInfoEntity.getStatus());
        int count1 = bookBorrowFlowMapper.insertSelective(bookBorrowEntity);
        if (count1 != 1) {
            throw new ValidationException(CREATE_BOOK_FLOW_ERROR.getCode(), CREATE_BOOK_FLOW_ERROR.getMessage());
        }
        int count2 = bookInfoMapper.updateByStatusAndBookId(bookId, bookInfoEntity.getStatus(), RENTAL.getCode());
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
     * 檢查書籍是否可借
     * @param bookInfoEntity
     * @return
     */
    private boolean isBookAvailable(BookInfoEntity bookInfoEntity) {
        return Objects.equals(bookInfoEntity.getStatus(), AVAILABLE.getCode());
    }

    /**
     * 創建借數流水實體
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
