package com.online.college.dao;

import com.online.college.pojo.AuthUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthUser record);

    int insertSelective(AuthUser record);

    AuthUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthUser record);

    int updateByPrimaryKey(AuthUser record);

    List<AuthUser> queryRecomd();

    AuthUser getByUsername(String username);

    AuthUser getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<AuthUser> queryPage(@Param("username") String username, @Param("status") Integer status);


}