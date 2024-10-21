package service;

import vo.CreateUserRequestVo;
import vo.UpdateUserRequestVo;
import com.dto.CreateUserResponseDto;
import com.dto.UpdateUserResponseDto;

public interface LibraryApiService {

    CreateUserResponseDto createUser(CreateUserRequestVo requestVo);

    UpdateUserResponseDto updateUser(UpdateUserRequestVo requestVo);
}
