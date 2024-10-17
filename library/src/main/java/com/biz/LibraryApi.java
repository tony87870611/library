package com.biz;

import com.api.LibraryService;
import com.dto.*;
import com.utils.ParameterCheck;
import com.utils.ValidationException;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.service.LibraryLocalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.enums.ErrorCode.SUCCESS;

@Service
public class LibraryApi implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryApi.class);

    @Resource
    private LibraryLocalService libraryLocalService;

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        logger.info("LibraryApi#createUser requestDto: {}", requestDto);
        try {
            ParameterCheck.createUserCheck(requestDto);
            libraryLocalService.createUser(requestDto);
        } catch (ValidationException e) {
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new CreateUserResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
        return new CreateUserResponseDto(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestDto requestDto) {
        logger.info("LibraryApi#updateUser requestDto: {}", requestDto);
        try {
            ParameterCheck.updateUserCheck(requestDto);
            libraryLocalService.updateUser(requestDto);
        } catch (ValidationException e) {
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new UpdateUserResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
        return new UpdateUserResponseDto(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @Override
    public QueryUserResponseDto queryUser(QueryUserRequestDto requestDto) {
        logger.info("LibraryApi#queryUser requestDto: {}", requestDto);
        QueryUserResponseDto responseDto = new QueryUserResponseDto();
        Pair<Integer, List<UserDto>> userPair = libraryLocalService.queryUser(requestDto);
        responseDto.setCount(userPair.getLeft());
        responseDto.setUserDtos(userPair.getRight());
        logger.info("LibraryApi#queryUser response: {}", responseDto);
        return responseDto;
    }

    @Override
    public QueryUserDetailResponseDto queryUserDetail(QueryUserDetailRequestDto requestDto) {
        logger.info("LibraryApi#queryUserDetail requestDto: {}", requestDto);
        QueryUserDetailResponseDto responseDto = new QueryUserDetailResponseDto();
        try {
            UserDto userDto = libraryLocalService.queryUserDetail(requestDto);
            responseDto.setUserDto(userDto);
            return responseDto;
        } catch (ValidationException e) {
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new QueryUserDetailResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
    }
}
