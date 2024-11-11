package com.mapper;

import com.entity.BookBorrowEntity;

public interface BookBorrowFlowMapper {
    int deleteByPrimaryKey(Integer primaryId);

    int insert(BookBorrowEntity record);

    int insertSelective(BookBorrowEntity record);

    BookBorrowEntity selectByPrimaryKey(Integer primaryId);

    int updateByPrimaryKeySelective(BookBorrowEntity record);

    int updateByPrimaryKey(BookBorrowEntity record);

    String selectBookNameByBookId(String bookId);
}