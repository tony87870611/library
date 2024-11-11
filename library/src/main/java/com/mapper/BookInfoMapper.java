package com.mapper;

import com.dto.BookCondition;
import com.entity.BookInfoEntity;

import java.util.List;

public interface BookInfoMapper {
    int deleteByPrimaryKey(Integer primaryId);

    int insert(BookInfoEntity record);

    int insertSelective(BookInfoEntity record);

    BookInfoEntity selectByPrimaryKey(Integer primaryId);

    int updateByBookIdSelective(BookInfoEntity record);

    int updateByPrimaryKey(BookInfoEntity record);

    int countByCondition(BookCondition condition);

    List<BookInfoEntity> selectByCondition(BookCondition condition);

    BookInfoEntity selectByBookId(String bookId);

    int updateByStatusAndBookId(String bookId, Integer originStatus, Integer targetStatus);
}