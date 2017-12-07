package com.online.college.dao;

import com.online.college.pojo.UserFollows;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFollowsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFollows record);

    int insertSelective(UserFollows record);

    UserFollows selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFollows record);

    int updateByPrimaryKey(UserFollows record);

    List<UserFollows> queryALl(@Param("userId") Integer userId, @Param("followId") Integer followId);

}