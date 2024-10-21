package com.dto;

import java.util.List;

public class QueryBookResponseDto extends BaseResponse{

    private Integer count;

    private List<BookDto> bookDtos;

    public QueryBookResponseDto() {}

    public QueryBookResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BookDto> getBookDtos() {
        return bookDtos;
    }

    public void setBookDtos(List<BookDto> bookDtos) {
        this.bookDtos = bookDtos;
    }

    @Override
    public String toString() {
        return "QueryBookResponseDto{" +
                "count=" + count +
                ", bookDtos=" + bookDtos +
                '}';
    }
}
