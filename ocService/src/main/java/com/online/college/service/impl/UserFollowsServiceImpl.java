package com.online.college.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.dao.UserFollowsMapper;
import com.online.college.pojo.UserFollowStudyRecord;
import com.online.college.pojo.UserFollows;
import com.online.college.service.IUserFollowsService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<UserFollows> getUserFollowList(Integer userId) {
        return userFollowsMapper.getUserFollowList(userId);
    }

    @Override
    public PageInfo queryFollowStudyRecord(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserFollowStudyRecord> list = userFollowsMapper.queryFollowStudyRecord(userId);
        //处理头像
        for (UserFollowStudyRecord item : list
             ) {
            if (StringUtils.isNotBlank(item.getHeader())) {
                item.setHeader(QiniuStorage.getUrl(item.getHeader()));
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
