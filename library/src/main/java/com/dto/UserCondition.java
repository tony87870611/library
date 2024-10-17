package com.dto;

public class UserCondition {

    private String accountName;

    private Integer status;

    private String createTimeBegin;

    private String createTimeEnd;

    private String updateTimeBegin;

    private String updateTimeEnd;

    private Integer pageNo;

    private Integer offset;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getUpdateTimeBegin() {
        return updateTimeBegin;
    }

    public void setUpdateTimeBegin(String updateTimeBegin) {
        this.updateTimeBegin = updateTimeBegin;
    }

    public String getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(String updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "UserCondition{" +
                "username='" + accountName + '\'' +
                ", status=" + status +
                ", createTimeBegin='" + createTimeBegin + '\'' +
                ", createTimeEnd='" + createTimeEnd + '\'' +
                ", updateTimeBegin='" + updateTimeBegin + '\'' +
                ", updateTimeEnd='" + updateTimeEnd + '\'' +
                ", pageNo=" + pageNo +
                ", offset=" + offset +
                '}';
    }
}
