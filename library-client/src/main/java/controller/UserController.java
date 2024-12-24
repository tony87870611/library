package controller;

import com.dto.QueryUserDetailResponseDto;
import com.dto.QueryUserResponseDto;
import org.springframework.web.bind.annotation.*;
import vo.*;
import com.dto.CreateUserResponseDto;
import com.dto.UpdateUserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.LibraryApiService;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static enums.ErrorCodes.SUCCESS;

@RestController("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private LibraryApiService libraryApiService;

    @PostMapping("/create")
    public Response createUser(@RequestBody CreateUserRequestVo requestVo) {
        logger.info("UserController#createUser request : {}", requestVo);
        CreateUserResponseDto responseDto = libraryApiService.createUser(requestVo);
        if (!Objects.equals(responseDto.getCode(), "000000")) {
            return new Response(responseDto.getCode(), responseDto.getMessage());
        }
        return new Response(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @PostMapping("/update")
    public Response updateUser(@RequestBody UpdateUserRequestVo requestVo) {
        logger.info("UserController#updateUser request : {}", requestVo);
        UpdateUserResponseDto responseDto = libraryApiService.updateUser(requestVo);
        if (!Objects.equals(responseDto.getCode(), "000000")) {
            return new Response(responseDto.getCode(), responseDto.getMessage());
        }
        return new Response(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @GetMapping("/query/detail")
    public Response queryUserDetail(@RequestParam String userId) {
        logger.info("UserController#queryUserDetail request : {}", userId);
        QueryUserDetailResponseVo responseVo = libraryApiService.queryUserDetail(userId);
        if (!Objects.equals(responseVo.getCode(), "000000")) {
            Map<String, Object> result = new HashMap<>();
            result.put("user", responseVo.getUser());
            return new Response(result, responseVo.getCode(), responseVo.getMessage());
        }
        return new Response(SUCCESS.getCode(), SUCCESS.getMessage());
    }

    @PostMapping("/query/list")
    public Response queryUserList(@RequestBody QueryUserListRequestVo requestVo) {
        logger.info("UserController#queryUserList request : {}", requestVo);
        QueryUserListResponseVo responseVo = libraryApiService.queryUserList(requestVo);
        if (!Objects.equals(responseVo.getCode(), "000000")) {
            Map<String, Object> result = new HashMap<>();
            result.put("users", responseVo.getUsers());
            return new Response(result, responseVo.getCode(), responseVo.getMessage());
        }
        return new Response(SUCCESS.getCode(), SUCCESS.getMessage());
    }

}
