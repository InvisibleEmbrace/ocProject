package com.online.college.service;

import com.github.pagehelper.PageInfo;
import com.online.college.pojo.AuthUser;

import java.util.List;

public interface IAuthUserService {

    AuthUser getUser(int id);

    /**
     * 获取首页推荐5个讲师
     **/
    List<AuthUser> queryRecomd(int pageNum, int pageSize);

    /**
     * 根据用户名获取讲师
     * @param username
     * @return
     */
    AuthUser getByUsername(String username);

    AuthUser getById(Integer userId);

    /**
     * 根据username和password获取
     **/
    AuthUser getByUsernameAndPassword(AuthUser authUser);

    /**
     * 创建用户
     */
    void insert(AuthUser authUser);

    void update(AuthUser authUser);

    PageInfo queryPage(String username, Integer status, Integer pageNum, Integer pageSize);
}
