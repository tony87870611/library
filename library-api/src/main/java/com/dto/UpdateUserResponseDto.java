package com.dto;

public class UpdateUserResponseDto extends BaseResponse {

    public UpdateUserResponseDto() {

    }

    public UpdateUserResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }
}
