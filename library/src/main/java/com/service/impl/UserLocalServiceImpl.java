package com.service.impl;

import com.dto.*;
import com.entity.UserEntity;
import com.google.gson.Gson;
import com.mapper.BookBorrowFlowMapper;
import com.mapper.UserMapper;
import com.utils.ValidationException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.service.UserLocalService;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.enums.ErrorCode.*;

@Service
public class UserLocalServiceImpl implements UserLocalService {

    private static final Logger logger = LoggerFactory.getLogger(UserLocalServiceImpl.class);

    private static final String USER_CACHE_PREFIX = "user:detail:";

    @Resource
    private UserMapper userMapper;

    @Resource
    private BookBorrowFlowMapper bookBorrowFlowMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Pair<Integer, List<UserDto>> queryUser(QueryUserRequestDto requestDto) {
        logger.info("LibraryLocalServiceImpl#queryUser requestDto: {}", requestDto);
        UserCondition userCondition = transfer2UserCondition(requestDto);
        int count = userMapper.countByCondition(userCondition);
        if (count == 0) {
            return ImmutablePair.of(0, null);
        }
        List<UserEntity> userEntities = userMapper.selectByCondition(userCondition);
        logger.info("LibraryLocalServiceImpl#queryUser userEntities: {}", userEntities);
        return ImmutablePair.of(count, transfer2UserDtoList(userEntities));
    }

    @Override
    public UserDto queryUserDetail(QueryUserDetailRequestDto requestDto) throws ValidationException {
        logger.info("LibraryLocalServiceImpl#queryUserDetail requestDto: {}", requestDto);
        String cacheKey = USER_CACHE_PREFIX + requestDto.getUserId();

        //從緩存中查詢用戶信息
        UserDto cachedUserDto = (UserDto) redisTemplate.opsForValue().get(cacheKey);
        if (cachedUserDto != null) {
            logger.info("Cache hit");
            return cachedUserDto;
        }

        //緩存未命中,查詢數據庫
        UserEntity userEntity = userMapper.selectByUserId(requestDto.getUserId());
        if (userEntity == null) {
            throw new ValidationException(USER_NOT_EXIST.getCode(), USER_NOT_EXIST.getMessage());
        }
        UserDto userDto = transfer2UserDto(userEntity);

        //將查詢結果寫入緩存中
        redisTemplate.opsForValue().set(cacheKey, userDto, 10, TimeUnit.MINUTES);
        logger.info("Cached set for key: {}", cacheKey);

        return userDto;
    }

    private UserDto transfer2UserDto(UserEntity source) {
        UserDto target = new UserDto();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    private UserCondition transfer2UserCondition(QueryUserRequestDto source) {
        UserCondition target = new UserCondition();
        BeanUtils.copyProperties(source, target);
        target.setOffset((source.getPageNo() - 1) * source.getPageSize());
        return target;
    }

    private List<UserDto> transfer2UserDtoList(List<UserEntity> sourceList) {
        List<UserDto> targetList = new ArrayList<>();
        for (UserEntity userEntity : sourceList) {
            UserDto target = new UserDto();
            BeanUtils.copyProperties(userEntity, target);
            targetList.add(target);
        }
        return targetList;
    }

    @Override
    public void createUser(CreateUserRequestDto requestDto) throws ValidationException {
        logger.info("LibraryLocalServiceImpl#createUser requestDto: {}", requestDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setAccountName(requestDto.getAccountName());
        userEntity.setPassword(requestDto.getPassword());
        userEntity.setUserId(UUID.randomUUID().toString().substring(0, 8));
        int count = userMapper.insertSelective(userEntity);
        if (count != 1) {
            throw new ValidationException(CREATE_USER_ERROR.getCode(), CREATE_USER_ERROR.getMessage());
        }
    }

    @Override
    public void updateUser(UpdateUserRequestDto requestDto) throws ValidationException {
        logger.info("LibraryLocalServiceImpl#updateUser requestDto: {}", requestDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(requestDto.getUserId());
        userEntity.setAccountName(requestDto.getAccountName());
        userEntity.setPassword(requestDto.getPassword());
        int count = userMapper.updateByUserIdSelective(userEntity);
        if (count != 1) {
            throw new ValidationException(UPDATE_USER_ERROR.getCode(), UPDATE_USER_ERROR.getMessage());
        }
    }
}
