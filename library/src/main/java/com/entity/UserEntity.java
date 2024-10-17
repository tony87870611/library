package com.entity;

import java.util.Date;

public class UserEntity {
    private String primaryId;

    private String userId;

    private String password;

    private String accountName;

    private Integer status;

    private Date datachangeCreatetime;

    private Date datachangeUpdatetime;

    public String getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId == null ? null : primaryId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
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
        return "UserEntity{" +
                "primaryId='" + primaryId + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", accountName='" + accountName + '\'' +
                ", status=" + status +
                ", datachangeCreatetime=" + datachangeCreatetime +
                ", datachangeUpdatetime=" + datachangeUpdatetime +
                '}';
    }
}