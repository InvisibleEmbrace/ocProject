package com.online.college.service;

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


}
