package service.impl;

import com.dto.*;
import utils.DateUtils;
import vo.*;
import com.api.LibraryService;
import org.springframework.stereotype.Service;
import service.LibraryApiService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryApiServiceImpl implements LibraryApiService {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Resource
    private LibraryService libraryService;

    public CreateUserResponseDto createUser(CreateUserRequestVo requestVo) {
        return libraryService.createUser(transfer2Dto(requestVo));
    }

    @Override
    public UpdateUserResponseDto updateUser(UpdateUserRequestVo requestVo) {
        return libraryService.updateUser(transfer2Dto(requestVo));
    }

    @Override
    public QueryUserDetailResponseVo queryUserDetail(String userId) {
        QueryUserDetailRequestDto requestDto = new QueryUserDetailRequestDto();
        requestDto.setUserId(userId);
        return transferDto2Vo(libraryService.queryUserDetail(requestDto));
    }

    @Override
    public QueryUserListResponseVo queryUserList(QueryUserListRequestVo requestVo) {
        return transferDto2Vo(libraryService.queryUser(transfer2Dto(requestVo)));
    }

    private List<UserVo> transfer2Vo(List<UserDto> source) {
        List<UserVo> target = new ArrayList<>();
        for (UserDto userDto : source) {
            UserVo userVo = new UserVo();
            userVo.setUserId(userDto.getUserId());
            userVo.setAccountName(userDto.getAccountName());
            userVo.setPassword(userDto.getPassword());
            userVo.setBookName(userDto.getBookName());
            userVo.setStatus(userDto.getStatus());
            target.add(userVo);
        }
        return target;
    }

    private QueryUserListResponseVo transferDto2Vo(QueryUserResponseDto responseDto) {
        QueryUserListResponseVo responseVo = new QueryUserListResponseVo();
        responseVo.setUsers(transfer2Vo(responseDto.getUserDtos()));
        responseVo.setCode(responseDto.getCode());
        responseVo.setMessage(responseDto.getMessage());
        return responseVo;
    }

    private UserVo generateUserVo(UserDto userDto) {
        UserVo userVo = new UserVo();
        userVo.setUserId(userDto.getUserId());
        userVo.setPassword(userDto.getPassword());
        userVo.setAccountName(userDto.getAccountName());
        userVo.setBookName(userDto.getBookName());
        userVo.setStatus(userDto.getStatus());
        userVo.setCreateTime(DateUtils.date2String(userDto.getCreateTime(), DATE_FORMAT));
        userVo.setUpdateTime(DateUtils.date2String(userDto.getCreateTime(), DATE_FORMAT));
        return userVo;
    }

    private QueryUserDetailResponseVo transferDto2Vo(QueryUserDetailResponseDto responseDto) {
        QueryUserDetailResponseVo responseVo = new QueryUserDetailResponseVo();
        responseVo.setUser(generateUserVo(responseDto.getUserDto()));
        responseVo.setCode(responseVo.getCode());
        responseVo.setMessage(responseVo.getMessage());
        return responseVo;
    }

    private QueryUserRequestDto transfer2Dto(QueryUserListRequestVo requestVo) {
        QueryUserRequestDto requestDto = new QueryUserRequestDto();
        requestDto.setAccountName(requestVo.getAccountName());
        requestDto.setStatus(requestVo.getStatus());
        requestDto.setCreateTimeBegin(requestVo.getCreateTimeBegin());
        requestDto.setCreateTimeEnd(requestVo.getCreateTimeEnd());
        requestDto.setUpdateTimeBegin(requestVo.getUpdateTimeBegin());
        requestDto.setUpdateTimeEnd(requestVo.getUpdateTimeEnd());
        requestDto.setPageNo(requestVo.getPageNo());
        requestDto.setPageSize(requestVo.getPageSize());
        return requestDto;
    }

    private CreateUserRequestDto transfer2Dto(CreateUserRequestVo requestVo) {
        CreateUserRequestDto requestDto = new CreateUserRequestDto();
        requestDto.setAccountName(requestVo.getAccountName());
        requestDto.setPassword(requestVo.getPassword());
        return requestDto;
    }

    private UpdateUserRequestDto transfer2Dto(UpdateUserRequestVo requestVo) {
        UpdateUserRequestDto requestDto = new UpdateUserRequestDto();
        requestDto.setUserId(requestVo.getUserId());
        requestDto.setAccountName(requestVo.getAccountName());
        requestDto.setPassword(requestVo.getPassword());
        return requestDto;
    }
}
