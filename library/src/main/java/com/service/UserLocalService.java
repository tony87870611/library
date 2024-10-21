package com.service;

import com.dto.*;
import com.utils.ValidationException;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface UserLocalService {

    Pair<Integer, List<UserDto>> queryUser(QueryUserRequestDto requestDto);

    UserDto queryUserDetail(QueryUserDetailRequestDto requestDto) throws ValidationException;

    void createUser(CreateUserRequestDto requestDto) throws ValidationException;

    void updateUser(UpdateUserRequestDto requestDto) throws ValidationException;
}
