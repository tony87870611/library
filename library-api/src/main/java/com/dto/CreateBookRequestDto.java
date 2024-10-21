package com.dto;

import java.io.Serializable;

public class CreateBookRequestDto implements Serializable {

    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "CreateBookRequestDto{" +
                "bookName='" + bookName + '\'' +
                '}';
    }
}
