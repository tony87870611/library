package com.dto;

public class CreateUserResponseDto extends BaseResponse{

    public CreateUserResponseDto() {

    }
    public CreateUserResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }



}
