package com.enums;

import java.util.Objects;

public enum ErrorCode {

    SUCCESS("000000","success"),
    PARAMETER_ERROR("100000","parameter error"),
    CREATE_USER_ERROR("100001","create user error"),
    UPDATE_USER_ERROR("100002","update user error"),
    USER_NOT_EXIST("100003","user not exist"),

    CREATE_BOOK_ERROR("110001","create book error"),
    UPDATE_BOOK_ERROR("110002","update book error"),
    UNKNOWN_ERROR("999999", "An unknown error occurred");

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // 根据错误代码获取枚举
    public static ErrorCode fromCode(String code) {
        for (ErrorCode errorCode : values()) {
            if (Objects.equals(errorCode.getCode(), code)) {
                return errorCode;
            }
        }
        return UNKNOWN_ERROR;
    }
}
