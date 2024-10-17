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
        requestDto.setAccountName("Tony");
        requestDto.setStatus(1);
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        QueryUserResponseDto responseDto = libraryApi.queryUser(requestDto);
        System.out.println("UpdateUserResponseDto : " + responseDto);
    }

    @Test
    public void testQueryUserDetail(){
        QueryUserDetailRequestDto requestDto = new QueryUserDetailRequestDto();
        requestDto.setUserId("bbb9698e");
        QueryUserDetailResponseDto responseDto = libraryApi.queryUserDetail(requestDto);
        System.out.println("UpdateUserResponseDto : " + responseDto);
    }

}
