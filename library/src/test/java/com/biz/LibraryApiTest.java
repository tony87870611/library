package com.biz;

import com.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class LibraryApiTest {

    @Resource
    private LibraryApi libraryApi;

    @Test
    public void testCreateUser() {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setAccountName("TonyTest2");
        requestDto.setPassword("Tony Test2");
        CreateUserResponseDto responseDto = libraryApi.createUser(requestDto);
        System.out.println("CreateUserResponseDto : " + responseDto);
    }

    @Test
    public void testUpdateUser() {
        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId("bbb9698e");
        requestDto.setAccountName("TonyTest3");
        requestDto.setPassword("Tony Test3");
        UpdateUserResponseDto responseDto = libraryApi.updateUser(requestDto);
        System.out.println("UpdateUserResponseDto : " + responseDto);
    }

    @Test
    public void testQueryUser() {
        QueryUserRequestDto requestDto = new QueryUserRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        QueryUserResponseDto responseDto = libraryApi.queryUser(requestDto);
        System.out.println("QueryUserResponseDto : " + responseDto);
    }

    @Test
    public void testQueryUserDetail(){
        QueryUserDetailRequestDto requestDto = new QueryUserDetailRequestDto();
        requestDto.setUserId("bbb9698e");
        QueryUserDetailResponseDto responseDto = libraryApi.queryUserDetail(requestDto);
        System.out.println("QueryUserDetailResponseDto : " + responseDto);
    }

    @Test
    public void testCreateBook(){
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setBookName("Math");
        CreateBookResponseDto responseDto = libraryApi.createBook(requestDto);
        System.out.println("CreateBookResponseDto : " + responseDto);
    }

    @Test
    public void testQueryBook() {
        QueryBookRequestDto requestDto = new QueryBookRequestDto();
//        requestDto.setBookName("Tony");
//        requestDto.setStatus(1);
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        QueryBookResponseDto responseDto = libraryApi.queryBook(requestDto);
        System.out.println("QueryBookResponse : " + responseDto);
    }

    @Test
    public void testUpdateBook(){
        UpdateBookRequestDto requestDto = new UpdateBookRequestDto();
        requestDto.setBookName("Taiwan History");
        UpdateBookResponseDto responseDto = libraryApi.updateBook(requestDto);
        System.out.println("UpdateBookResponseDto : " + responseDto);
    }

    @Test
    public void testBorrowBook(){
        BorrowBookRequestDto requestDto = new BorrowBookRequestDto();
        requestDto.setBookId("dd88f595-9eea-4c");
        requestDto.setUserId("Tony Test1");
        BorrowBookResponseDto responseDto = libraryApi.borrowBook(requestDto);
        System.out.println("BorrowBookResponseDto : " + responseDto);
    }

    @Test
    public void testReturnBook(){
        String bookId = "dd88f595-9eea-4c";
        ReturnBookResponseDto responseDto = libraryApi.returnBook(bookId);
        System.out.println("BorrowBookResponseDto : " + responseDto);
    }
}
