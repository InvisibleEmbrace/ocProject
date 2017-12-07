package com.online.college.service.impl;

import com.online.college.dao.UserFollowsMapper;
import com.online.college.pojo.UserFollows;
import com.online.college.service.IUserCollectionsService;
import com.online.college.service.IUserFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户关注
 */
@Service("iUserFollowsService")
public class UserFollowsServiceImpl implements IUserFollowsService {

    @Autowired
    private UserFollowsMapper userFollowsMapper;

    @Override
    public List<UserFollows> queryALl(Integer userId, Integer followId) {
        return userFollowsMapper.queryALl(userId, followId);
    }

    @Override
    public void delete(Integer id) {
        userFollowsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(UserFollows userFollows) {
        userFollowsMapper.insertSelective(userFollows);
    }
}
