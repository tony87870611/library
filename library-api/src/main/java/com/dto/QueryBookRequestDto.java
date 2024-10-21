package com.dto;

import java.io.Serializable;

public class QueryBookRequestDto implements Serializable {

    private String bookName;

    private Integer status;

    private Integer pageNo;

    private Integer pageSize;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryBookRequestDto{" +
                "bookName='" + bookName + '\'' +
                ", status=" + status +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
