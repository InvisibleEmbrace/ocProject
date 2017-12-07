package com.online.college.service;

import com.online.college.pojo.UserCollections;

import java.util.List;

/**
 * 用户收藏
 */
public interface IUserCollectionsService {

    /**
     * 获取用户收藏
     * @param userid
     * @param classifyId
     * @param objectId
     * @return
     */
    List<UserCollections> getCollection(Integer userid, Integer classifyId, Integer objectId);

    /**
     * 删除收藏
     *
     * @param
     */
    void delete(UserCollections userCollections);

    /**
     * 插入收藏数据
     * @param userCollections
     */
    void insert(UserCollections userCollections);
}
