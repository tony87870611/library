package service;

import Vo.CreateUserRequestVo;
import Vo.UpdateUserRequestVo;
import com.dto.CreateUserRequestDto;
import com.dto.CreateUserResponseDto;
import com.dto.UpdateUserResponseDto;

public interface LibraryApiService {

    CreateUserResponseDto createUser(CreateUserRequestVo requestVo);

    UpdateUserResponseDto updateUser(UpdateUserRequestVo requestVo);
}
