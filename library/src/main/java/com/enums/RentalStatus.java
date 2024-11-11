package com.enums;

public enum RentalStatus {

    AVAILABLE(0),
    RENTAL(1);

    private Integer code;

    // 构造函数
    RentalStatus(Integer code) {
        this.code = code;
    }

    // 获取枚举对应的整数值
    public Integer getCode() {
        return code;
    }

    // 根据整数值获取对应的枚举
    public static RentalStatus fromCode(int code) {
        for (RentalStatus status : RentalStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

}
