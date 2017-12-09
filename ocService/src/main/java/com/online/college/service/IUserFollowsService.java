package com.online.college.service;

import com.github.pagehelper.PageInfo;
import com.online.college.pojo.UserFollows;

import java.util.List;

/**
 * 用户关注
 */
public interface IUserFollowsService {

    List<UserFollows> queryALl(Integer userId, Integer followId);

    void delete(Integer id);

    void insert(UserFollows userFollows);

    List<UserFollows> getUserFollowList(Integer userId);

    PageInfo queryFollowStudyRecord(Integer userId, Integer pageNum, Integer pageSize);
}
