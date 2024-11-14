package com.enums;

public enum BookStatus {
    AVAILABLE(0),
    RENTAL(1);

    private Integer code;

    // 构造函数
    BookStatus(Integer code) {
        this.code = code;
    }

    // 获取枚举对应的整数值
    public Integer getCode() {
        return code;
    }

    // 根据整数值获取对应的枚举
    public static BookStatus fromCode(int code) {
        for (BookStatus status : BookStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
