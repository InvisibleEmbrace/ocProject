package com.online.college.dao;

import com.online.college.pojo.UserFollows;

public interface UserFollowsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFollows record);

    int insertSelective(UserFollows record);

    UserFollows selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFollows record);

    int updateByPrimaryKey(UserFollows record);
}