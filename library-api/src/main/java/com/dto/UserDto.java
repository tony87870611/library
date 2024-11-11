package com.dto;

import java.util.Date;
import java.util.List;

public class UserDto {

    /**
     * 用戶編號
     */
    private String userId;
    /**
     * 帳戶名
     */
    private String accountName;
    /**
     * 密碼
     */
    private String password;
    /**
     * 帳號狀態
     */
    private Integer status;
    /**
     * 租借的書名
     */
    private List<String> bookName;
    /**
     * 最後創建時間
     */
    private Date createTime;
    /**
     * 最後更新時間
     */
    private Date updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getBookName() {
        return bookName;
    }

    public void setBookName(List<String> bookName) {
        this.bookName = bookName;
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
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", bookName=" + bookName +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
