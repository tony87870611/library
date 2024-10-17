package com.service.impl;

import com.dto.QueryUserRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.service.LibraryLocalService;

import javax.annotation.Resource;


@SpringBootTest
public class LibraryLocalServiceImplTest {

    @Resource
    private LibraryLocalService service;

    @Test
    public void testQueryUser() {
        QueryUserRequestDto requestDto = new QueryUserRequestDto();
        requestDto.setAccountName("tonytony");
    }
}
