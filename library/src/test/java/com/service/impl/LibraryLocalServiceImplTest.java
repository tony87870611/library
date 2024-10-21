package com.service.impl;

import com.dto.QueryUserRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.service.UserLocalService;

import javax.annotation.Resource;


@SpringBootTest
public class LibraryLocalServiceImplTest {

    @Resource
    private UserLocalService service;

    @Test
    public void testQueryUser() {
        QueryUserRequestDto requestDto = new QueryUserRequestDto();
        requestDto.setAccountName("tonytony");
    }
}
