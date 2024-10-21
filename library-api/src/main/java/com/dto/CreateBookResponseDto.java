package com.dto;

public class CreateBookResponseDto extends BaseResponse{

    public CreateBookResponseDto() {

    }
    public CreateBookResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }
}
