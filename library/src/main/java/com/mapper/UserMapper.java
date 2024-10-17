package com.mapper;

import com.dto.UserCondition;
import com.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String primaryId);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(String primaryId);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    int updateByUserIdSelective(UserEntity record);

    List<UserEntity> selectByCondition(UserCondition condition);

    int countByCondition(UserCondition condition);

    UserEntity selectByUserId(@Param("userId") String userId);
}