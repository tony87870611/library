package com.entity;

import java.util.Date;

public class BookBorrowEntity {
    private Integer primaryId;

    private String serialNo;

    private String bookId;

    private String userId;

    private Integer status;

    private Date datachangeCreatetime;

    private Date datachangeUpdatetime;

    public Integer getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Integer primaryId) {
        this.primaryId = primaryId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
}