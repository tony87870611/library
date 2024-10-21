package com.dto;

import java.util.Date;

public class BookDto {

    /**
     * 書編號
     */
    private String bookId;
    /**
     * 書名
     */
    private String bookName;
    /**
     * 租借狀態
     */
    private Integer status;
    /**
     * 最後創建時間
     */
    private Date createTime;
    /**
     * 最後更新時間
     */
    private Date updateTime;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
