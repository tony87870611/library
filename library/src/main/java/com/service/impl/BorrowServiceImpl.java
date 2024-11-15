package com.service.impl;

import com.dto.BorrowBookRequestDto;
import com.entity.BookBorrowEntity;
import com.entity.BookInfoEntity;
import com.mapper.BookBorrowFlowMapper;
import com.mapper.BookInfoMapper;
import com.service.BorrowService;
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
import static com.enums.BorrowFlowStatus.BORROW;
import static com.enums.ErrorCode.*;


@Service
public class BorrowServiceImpl implements BorrowService {

    private static final String BOOK_LOCK_KEY_PREFIX = "BOOK_LOCK_";

    @Resource
    private BookBorrowFlowMapper bookBorrowFlowMapper;

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Transactional
    public void borrowBook(BorrowBookRequestDto requestDto) throws ValidationException {

        String lockKey = BOOK_LOCK_KEY_PREFIX + requestDto.getBookId();
        String lockValue = UUID.randomUUID().toString();

        try {
            //嘗試獲取分布式鎖
            if (!acquireLock(lockKey, lockValue)) {
                throw new ValidationException(BOOK_LOCK_ERROR.getCode(), BOOK_LOCK_ERROR.getMessage());
            }
            performBorrowing(requestDto);
        } finally {
            //釋放鎖
            releaseLock(lockKey, lockValue);
        }
    }

    private void performBorrowing(BorrowBookRequestDto requestDto) throws ValidationException {
        //查詢書籍訊息
        BookInfoEntity bookInfoEntity = bookInfoMapper.selectByBookId(requestDto.getBookId());
        if (bookInfoEntity == null) {
            throw new ValidationException(BOOK_NOT_FOUND.getCode(), BOOK_NOT_FOUND.getMessage());
        }

        //檢查書籍是否可借
        if (!isBookAvailable(bookInfoEntity)) {
            throw new ValidationException(BOOK_NOT_AVAILABLE.getCode(), BOOK_NOT_AVAILABLE.getMessage());
        }
        //插入一筆借書流水
        BookBorrowEntity bookBorrowEntity = buildBookBorrowEntity(requestDto.getBookId(), requestDto.getUserId(), BORROW.getCode());
        int count1 = bookBorrowFlowMapper.insertSelective(bookBorrowEntity);
        if (count1 != 1) {
            throw new ValidationException(CREATE_BOOK_FLOW_ERROR.getCode(), CREATE_BOOK_FLOW_ERROR.getMessage());
        }
        //更新書籍狀態從可借書到租借中
        int count2 = bookInfoMapper.updateByStatusAndBookId(requestDto.getBookId(), AVAILABLE.getCode(), RENTAL.getCode());
        if (count2 != 1) {
            throw new ValidationException(UPDATE_BOOK_STATUS_ERROR.getCode(), UPDATE_BOOK_STATUS_ERROR.getMessage());
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
     *
     * @param bookInfoEntity
     * @return
     */
    private boolean isBookAvailable(BookInfoEntity bookInfoEntity) {
        return Objects.equals(bookInfoEntity.getStatus(), AVAILABLE.getCode());
    }

    /**
     * 創建借書流水實體
     *
     * @param bookId
     * @param currentStatus
     * @return
     */
    private BookBorrowEntity buildBookBorrowEntity(String bookId, String userId, Integer currentStatus) {
        BookBorrowEntity bookBorrowEntity = new BookBorrowEntity();
        bookBorrowEntity.setBookId(bookId);
        bookBorrowEntity.setSerialNo(UUID.randomUUID().toString().substring(0, 16));
        bookBorrowEntity.setStatus(currentStatus);
        bookBorrowEntity.setUserId(userId);
        return bookBorrowEntity;
    }
}
