package com.online.college.service.impl;

import com.online.college.dao.UserCollectionsMapper;
import com.online.college.pojo.UserCollections;
import com.online.college.service.IUserCollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户收藏
 */
@Service("iUserCollectionsService")
public class UserCollectionsServiceImpl implements IUserCollectionsService{

    @Autowired
    private UserCollectionsMapper userCollectionsMapper;

    @Override
    public List<UserCollections> getCollection(Integer userid, Integer classifyId, Integer objectId) {
        return userCollectionsMapper.getCollection(userid, classifyId, objectId);
    }

    @Override
    public void delete(UserCollections userCollections) {
        userCollectionsMapper.delete(userCollections);
    }


    @Override
    public void insert(UserCollections userCollections) {
        userCollectionsMapper.insertSelective(userCollections);
    }
}
