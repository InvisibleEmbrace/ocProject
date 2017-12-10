package com.online.college.dao;

import com.online.college.pojo.UserCollections;
import com.online.college.pojo.UserCollectionsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCollectionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCollections record);

    int insertSelective(UserCollections record);

    UserCollections selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCollections record);

    int updateByPrimaryKey(UserCollections record);

    List<UserCollections> getCollection(@Param("userId") Integer userId, @Param("classifyId") Integer classifyId, @Param("objectId") Integer objectId);

    void delete(UserCollections userCollections);

    List<UserCollectionsDto> queryPage(Integer userId);
}