package com.service.impl;

import com.dto.*;
import com.entity.BookBorrowEntity;
import com.entity.BookInfoEntity;
import com.mapper.BookInfoMapper;
import com.service.BookLocalService;
import com.utils.DateUtils;
import com.utils.ValidationException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.enums.ErrorCode.CREATE_BOOK_ERROR;
import static com.enums.ErrorCode.UPDATE_BOOK_ERROR;

@Service
public class BookLocalServiceImpl implements BookLocalService {

    private static final Logger logger = LoggerFactory.getLogger(BookLocalServiceImpl.class);

    private static String datePattern = "yyyy-MM-dd HH:mm:ss";

    @Resource
    private BookInfoMapper bookInfoMapper;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public void createBook(CreateBookRequestDto requestDto) throws ValidationException {
        logger.info("BookLocalServiceImpl#createBook requestDto: {}", requestDto);
        BookInfoEntity bookInfoEntity = new BookInfoEntity();
        bookInfoEntity.setBookId(UUID.randomUUID().toString().substring(0, 16));
        bookInfoEntity.setBookName(requestDto.getBookName());
        int count = bookInfoMapper.insertSelective(bookInfoEntity);
        if (count != 1) {
            throw new ValidationException(CREATE_BOOK_ERROR.getCode(), CREATE_BOOK_ERROR.getMessage());
        }
    }

    @Override
    public void updateBook(UpdateBookRequestDto requestDto) throws ValidationException {
        logger.info("BookLocalServiceImpl#updateBook requestDto: {}", requestDto);
        BookInfoEntity bookInfoEntity = new BookInfoEntity();
        bookInfoEntity.setBookId(requestDto.getBookId());
        bookInfoEntity.setBookName(requestDto.getBookName());
        int count = bookInfoMapper.updateByBookIdSelective(bookInfoEntity);
        if (count != 1) {
            throw new ValidationException(UPDATE_BOOK_ERROR.getCode(), UPDATE_BOOK_ERROR.getMessage());
        }
    }

    @Override
    public Pair<Integer, List<BookDto>> queryBook(QueryBookRequestDto requestDto) {
        logger.info("BookLocalServiceImpl#queryBook requestDto: {}", requestDto);

        //根據請求Dto創建Redis鍵
        String redisKey = "book_query:" + requestDto.hashCode();
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();

        //查詢快取
        Pair<Integer, List<BookDto>> cachedResult = (Pair<Integer, List<BookDto>>) opsForValue.get(redisKey);
        if (cachedResult != null) {
            return cachedResult;
        }

        BookCondition condition = transfer2BookCondition(requestDto);
        int count = bookInfoMapper.countByCondition(condition);
        if (count == 0) {
            return ImmutablePair.of(0, null);
        }
        List<BookInfoEntity> bookInfoEntities = bookInfoMapper.selectByCondition(condition);
        Pair<Integer, List<BookDto>> result = ImmutablePair.of(count, transfer2BookDtoList(bookInfoEntities));

        //將查詢結果存入Redis並設置過期時間
        opsForValue.set(redisKey, result, 10, TimeUnit.MINUTES);

        return result;
    }

    private List<BookDto> transfer2BookDtoList(List<BookInfoEntity> sourceList) {
        return sourceList.stream()
                .map(source -> {
                    BookDto target = new BookDto();
                    target.setBookId(source.getBookId());
                    target.setBookName(source.getBookName());
                    target.setStatus(source.getStatus());
                    target.setCreateTime(source.getDatachangeCreatetime());
                    target.setUpdateTime(source.getDatachangeUpdatetime());
                    return target;
                })
                .collect(Collectors.toList());
    }

    private BookCondition transfer2BookCondition(QueryBookRequestDto source) {
        BookCondition condition = new BookCondition();
        condition.setBookName(source.getBookName());
        condition.setStatus(source.getStatus());
        condition.setPageNo(source.getPageNo());
        condition.setOffset((source.getPageNo() - 1) * source.getPageSize());
        return condition;
    }
}
