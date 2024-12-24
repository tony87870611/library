package service;

import com.dto.QueryUserDetailRequestDto;
import com.dto.QueryUserDetailResponseDto;
import vo.*;
import com.dto.CreateUserResponseDto;
import com.dto.UpdateUserResponseDto;

public interface LibraryApiService {

    CreateUserResponseDto createUser(CreateUserRequestVo requestVo);

    UpdateUserResponseDto updateUser(UpdateUserRequestVo requestVo);

    QueryUserDetailResponseVo queryUserDetail(String userId);

    QueryUserListResponseVo queryUserList(QueryUserListRequestVo requestVo);
}
