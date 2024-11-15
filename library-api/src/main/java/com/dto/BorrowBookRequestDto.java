package com.dto;

import java.io.Serializable;

public class BorrowBookRequestDto implements Serializable {

    private String bookId;

    private String userId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BorrowBookRequestDto{" +
                "bookId='" + bookId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
