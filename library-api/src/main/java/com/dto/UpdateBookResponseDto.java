package com.dto;

public class UpdateBookResponseDto extends BaseResponse{

    public UpdateBookResponseDto() {

    }

    public UpdateBookResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }
}
