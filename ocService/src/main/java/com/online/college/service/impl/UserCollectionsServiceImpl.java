package com.online.college.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.college.dao.UserCollectionsMapper;
import com.online.college.pojo.UserCollections;
import com.online.college.pojo.UserCollectionsDto;
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

    @Override
    public PageInfo queryMyCollection(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserCollectionsDto> list = userCollectionsMapper.queryPage(userId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
