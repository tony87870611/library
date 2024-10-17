package com.dto;

import java.io.Serializable;
import java.util.List;

public class QueryUserResponseDto extends BaseResponse {

    private Integer count;

    private List<UserDto> userDtos;

    public QueryUserResponseDto() {

    }

    public QueryUserResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    @Override
    public String toString() {
        return "QueryUserResponseDto{" +
                "count=" + count +
                ", userDtos=" + userDtos +
                '}';
    }
}
