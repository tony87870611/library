package com.dto;

public class QueryUserDetailResponseDto extends BaseResponse{

    private UserDto userDto;

    public QueryUserDetailResponseDto() {

    }

    public QueryUserDetailResponseDto(String errorCode, String errorMessage){
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "QueryUserDetailResponseDto{" +
                "userDto=" + userDto +
                '}';
    }
}
