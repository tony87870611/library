package controller;

import vo.Response;
import vo.CreateUserRequestVo;
import vo.UpdateUserRequestVo;
import com.dto.CreateUserResponseDto;
import com.dto.UpdateUserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.LibraryApiService;

import javax.annotation.Resource;

import java.util.Objects;

import static enums.ErrorCodes.SUCCESS;

@RestController("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private LibraryApiService libraryApiService;

    @PostMapping("/create")
    public Response createUser(CreateUserRequestVo requestVo){
        logger.info("UserController#createUser request : {}", requestVo);
        CreateUserResponseDto responseDto = libraryApiService.createUser(requestVo);
        if(!Objects.equals(responseDto.getCode(), "000000")){
            return new Response(responseDto.getCode(), responseDto.getMessage());
        }
        return new Response(SUCCESS.getCode(),SUCCESS.getMessage());
    }

    @PostMapping("/update")
    public Response updateUser(UpdateUserRequestVo requestVo){
        logger.info("UserController#updateUser request : {}", requestVo);
        UpdateUserResponseDto responseDto = libraryApiService.updateUser(requestVo);
        if(!Objects.equals(responseDto.getCode(), "000000")){
            return new Response(responseDto.getCode(), responseDto.getMessage());
        }
        return new Response(SUCCESS.getCode(),SUCCESS.getMessage());
    }


}
