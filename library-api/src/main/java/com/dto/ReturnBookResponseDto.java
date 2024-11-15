package com.dto;

public class ReturnBookResponseDto extends BaseResponse{

    public ReturnBookResponseDto() {}

    public ReturnBookResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }
}
