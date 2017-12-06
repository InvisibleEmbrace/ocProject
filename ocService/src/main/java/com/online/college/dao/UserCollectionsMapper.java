package com.online.college.dao;

import com.online.college.pojo.UserCollections;

public interface UserCollectionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCollections record);

    int insertSelective(UserCollections record);

    UserCollections selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCollections record);

    int updateByPrimaryKey(UserCollections record);
}