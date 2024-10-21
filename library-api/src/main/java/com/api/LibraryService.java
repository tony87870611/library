package com.api;

import com.dto.*;

public interface LibraryService {

    CreateUserResponseDto createUser(CreateUserRequestDto requestDto);

    UpdateUserResponseDto updateUser(UpdateUserRequestDto requestDto);

    QueryUserResponseDto queryUser(QueryUserRequestDto requestDto);

    QueryUserDetailResponseDto queryUserDetail(QueryUserDetailRequestDto requestDto);

    CreateBookResponseDto createBook(CreateBookRequestDto requestDto);

    UpdateBookResponseDto updateBook(UpdateBookRequestDto requestDto);

    QueryBookResponseDto queryBook(QueryBookRequestDto requestDto);


}
