package com.entity;

import java.util.Date;

public class BookInfoEntity {
    private Integer primaryId;

    private String bookId;

    private String bookName;

    private Integer status;

    private Date datachangeCreatetime;

    private Date datachangeUpdatetime;

    public Integer getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Integer primaryId) {
        this.primaryId = primaryId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

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

    public Date getDatachangeCreatetime() {
        return datachangeCreatetime;
    }

    public void setDatachangeCreatetime(Date datachangeCreatetime) {
        this.datachangeCreatetime = datachangeCreatetime;
    }

    public Date getDatachangeUpdatetime() {
        return datachangeUpdatetime;
    }

    public void setDatachangeUpdatetime(Date datachangeUpdatetime) {
        this.datachangeUpdatetime = datachangeUpdatetime;
    }

    @Override
    public String toString() {
        return "BookInfoEntity{" +
                "primaryId=" + primaryId +
                ", bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", status=" + status +
                ", datachangeCreatetime=" + datachangeCreatetime +
                ", datachangeUpdatetime=" + datachangeUpdatetime +
                '}';
    }
}