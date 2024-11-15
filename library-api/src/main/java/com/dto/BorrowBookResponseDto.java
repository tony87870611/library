package com.dto;

public class BorrowBookResponseDto extends BaseResponse{

    public BorrowBookResponseDto() {}

    public BorrowBookResponseDto(String errorCode, String errorMessage) {
        this.setCode(errorCode);
        this.setMessage(errorMessage);
    }
}
