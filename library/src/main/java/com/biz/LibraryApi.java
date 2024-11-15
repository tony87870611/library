package com.biz;

import com.api.LibraryService;
import com.dto.*;
import com.service.BookLocalService;
import com.service.BorrowService;
import com.service.ReturnService;
import com.utils.ParameterCheck;
import com.utils.ValidationException;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.service.UserLocalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.enums.ErrorCode.SUCCESS;

@Service
public class LibraryApi implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryApi.class);

    @Resource
    private UserLocalService userLocalService;

    @Resource
    private BookLocalService bookLocalService;

    @Resource
    private ReturnService returnService;

    @Resource
    private BorrowService borrowService;

    @Override
    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        logger.info("LibraryApi#createUser requestDto: {}", requestDto);
        try {
            ParameterCheck.createUserCheck(requestDto);
            userLocalService.createUser(requestDto);
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
            userLocalService.updateUser(requestDto);
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
        Pair<Integer, List<UserDto>> userPair = userLocalService.queryUser(requestDto);
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
            UserDto userDto = userLocalService.queryUserDetail(requestDto);
            responseDto.setUserDto(userDto);
            return responseDto;
        } catch (ValidationException e) {
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new QueryUserDetailResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
    }

    @Override
    public CreateBookResponseDto createBook(CreateBookRequestDto requestDto) {
        logger.info("LibraryApi#createBook requestDto: {}", requestDto);
        try {
            ParameterCheck.createBookCheck(requestDto);
            bookLocalService.createBook(requestDto);
        } catch (ValidationException e) {
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new CreateBookResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
        return new CreateBookResponseDto(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @Override
    public UpdateBookResponseDto updateBook(UpdateBookRequestDto requestDto) {
        logger.info("LibraryApi#updateBook requestDto: {}", requestDto);
        try {
            ParameterCheck.updateBookCheck(requestDto);
            bookLocalService.updateBook(requestDto);
        } catch (ValidationException e) {
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new UpdateBookResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
        return new UpdateBookResponseDto(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @Override
    public QueryBookResponseDto queryBook(QueryBookRequestDto requestDto) {
        logger.info("LibraryApi#queryBook requestDto: {}", requestDto);
        QueryBookResponseDto responseDto = new QueryBookResponseDto();
        Pair<Integer, List<BookDto>> userPair = bookLocalService.queryBook(requestDto);
        responseDto.setCount(userPair.getLeft());
        responseDto.setBookDtos(userPair.getRight());
        logger.info("LibraryApi#queryBook response: {}", responseDto);
        return responseDto;
    }

    @Override
    public BorrowBookResponseDto borrowBook(BorrowBookRequestDto requestDto) {
        logger.info("LibraryApi#borrowBook requestDto: {}", requestDto);
        try{
            borrowService.borrowBook(requestDto);
        }catch (ValidationException e){
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new BorrowBookResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
        return new BorrowBookResponseDto(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @Override
    public ReturnBookResponseDto returnBook(String bookId) {
        logger.info("LibraryApi#returnBook requestDto: {}", bookId);
        try{
            returnService.returnBook(bookId);
        }catch (ValidationException e){
            logger.error("Error occurred with code: {}, message: {}", e.getErrorCode(), e.getErrorMessage());
            return new ReturnBookResponseDto(e.getErrorCode(), e.getErrorMessage());
        }
        return new ReturnBookResponseDto(SUCCESS.getCode(), SUCCESS.getMessage());
    }

}
