package com.enums;

public enum BorrowFlowStatus {

    BORROW(0),
    RETURN(1);

    private Integer code;

    // 構造函數
    BorrowFlowStatus(Integer code) {
        this.code = code;
    }

    // 獲取枚舉對應整數值
    public Integer getCode() {
        return code;
    }

    // 根據整數值獲取對應的枚舉
    public static BorrowFlowStatus fromCode(int code) {
        for (BorrowFlowStatus status : BorrowFlowStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

}
