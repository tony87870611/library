package com.dto;

import javax.xml.bind.ValidationException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseResponse implements Serializable {


    private static final String DEFAULT_CODE = "000000";

    private static final String DEFAULT_MESSAGE = "success";

    private String code;

    private String message;

    private Map<String, Object> result;

    public BaseResponse() {
        this.code = DEFAULT_CODE;
        this.message = DEFAULT_MESSAGE;
        this.result = new HashMap<>();
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.result = new HashMap<>();
    }

    public BaseResponse(ValidationException e) {
        this.code = e.getErrorCode();
        this.message = e.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
