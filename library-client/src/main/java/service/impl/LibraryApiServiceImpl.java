package service.impl;

import Vo.CreateUserRequestVo;
import Vo.UpdateUserRequestVo;
import com.api.LibraryService;
import com.dto.CreateUserRequestDto;
import com.dto.CreateUserResponseDto;
import com.dto.UpdateUserRequestDto;
import com.dto.UpdateUserResponseDto;
import org.springframework.stereotype.Service;
import service.LibraryApiService;

import javax.annotation.Resource;

@Service
public class LibraryApiServiceImpl implements LibraryApiService {

    @Resource
    private LibraryService libraryService;

    public CreateUserResponseDto createUser(CreateUserRequestVo requestVo) {
        return libraryService.createUser(transfer2Dto(requestVo));
    }

    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestVo requestVo) {
        return libraryService.updateUser(transfer2Dto(requestVo));
    }

    public CreateUserRequestDto transfer2Dto(CreateUserRequestVo requestVo) {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setAccountName(requestVo.getAccountName());
        requestDto.setPassword(requestVo.getPassword());
        return requestDto;
    }

    public UpdateUserRequestDto transfer2Dto(UpdateUserRequestVo requestVo) {
        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(requestVo.getUserId());
        requestDto.setAccountName(requestVo.getAccountName());
        requestDto.setPassword(requestVo.getPassword());
        return requestDto;
    }
}
