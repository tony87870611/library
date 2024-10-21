package com.utils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.dto.CreateBookRequestDto;
import com.dto.CreateUserRequestDto;
import com.dto.UpdateBookRequestDto;
import com.dto.UpdateUserRequestDto;

import static com.enums.ErrorCode.PARAMETER_ERROR;


public class ParameterCheck {

    public static void createUserCheck(CreateUserRequestDto requestDto) throws ValidationException {
        //校驗帳號名
        if (StringUtils.isNotEmpty(requestDto.getAccountName())) {
            String accountName = requestDto.getAccountName().trim();
            if (accountName.length() < 6 || accountName.length() > 20) {
                throw new ValidationException(PARAMETER_ERROR.getCode(), "account name must be between 6 and 20 characters");
            }
            //校驗帳號名只能包含字母和數字
            if(!requestDto.getAccountName().matches("^[a-zA-Z0-9]+$")){
                throw new ValidationException(PARAMETER_ERROR.getCode(), "account name can only contain letters and numbers");
            }
        }else{
            requestDto.setAccountName(null);
        }

        //校驗密碼
        if (StringUtils.isNotEmpty(requestDto.getPassword())) {
            if (requestDto.getPassword().trim().length() < 6 || requestDto.getPassword().trim().length() > 20) {
                throw new ValidationException(PARAMETER_ERROR.getCode(), "password must be between 6 and 20 characters");
            }
            //校验密码必须包含至少一个大写字母
            if(!requestDto.getPassword().matches(".*[A-Z].*")){
                throw new ValidationException(PARAMETER_ERROR.getCode(), "password must contain at least one uppercase letter");
            }
            // 校验密码必须包含至少一个小写字母
            if(!requestDto.getPassword().matches(".*[a-z].*")){
                throw new ValidationException(PARAMETER_ERROR.getCode(), "password must contain at least one lowercase letter");
            }
        }
        else{
            requestDto.setPassword(null);
        }
    }

    public static void updateUserCheck(UpdateUserRequestDto requestDto) throws ValidationException {
        //校驗帳號名
        if (StringUtils.isNotEmpty(requestDto.getAccountName())) {
            String accountName = requestDto.getAccountName().trim();
            if (accountName.length() < 6 || accountName.length() > 20) {
                throw new ValidationException(PARAMETER_ERROR.getCode(), "account name must be between 6 and 20 characters");
            }
            //校驗帳號名只能包含字母和數字
            if(!requestDto.getAccountName().matches("^[a-zA-Z0-9]+$")){
                throw new ValidationException(PARAMETER_ERROR.getCode(), "account name can only contain letters and numbers");
            }
        }else{
            requestDto.setAccountName(null);
        }

        //校驗密碼
        if (StringUtils.isNotEmpty(requestDto.getPassword())) {
            if (requestDto.getPassword().trim().length() < 6 || requestDto.getPassword().trim().length() > 20) {
                throw new ValidationException(PARAMETER_ERROR.getCode(), "password must be between 6 and 20 characters");
            }
            //校验密码必须包含至少一个大写字母
            if(!requestDto.getPassword().matches(".*[A-Z].*")){
                throw new ValidationException(PARAMETER_ERROR.getCode(), "password must contain at least one uppercase letter");
            }
            // 校验密码必须包含至少一个小写字母
            if(!requestDto.getPassword().matches(".*[a-z].*")){
                throw new ValidationException(PARAMETER_ERROR.getCode(), "password must contain at least one lowercase letter");
            }
        }
        else{
            requestDto.setPassword(null);
        }
    }

    public static void createBookCheck(CreateBookRequestDto requestDto) throws ValidationException{
        //校驗書名
        if(StringUtils.isEmpty(requestDto.getBookName()) ){
            throw new ValidationException(PARAMETER_ERROR.getCode(), "book name can not be empty");
        }
    }

    public static void updateBookCheck(UpdateBookRequestDto requestDto) throws ValidationException{
        //較驗前端是否船bookId
        if(StringUtils.isEmpty(requestDto.getBookId())){
            throw new ValidationException(PARAMETER_ERROR.getCode(), "book name can not be empty");
        }
        //校驗書名
        if(StringUtils.isEmpty(requestDto.getBookName()) ){
            throw new ValidationException(PARAMETER_ERROR.getCode(), "book name can not be empty");
        }
    }
}
