package com.dto;

import java.io.Serializable;

public class QueryUserDetailRequestDto implements Serializable {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QueryUserDetailRequestDto{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
