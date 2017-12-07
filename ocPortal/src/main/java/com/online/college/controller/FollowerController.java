package com.online.college.controller;

import com.online.college.common.web.SessionContext;
import com.online.college.pojo.UserFollows;
import com.online.college.service.IUserFollowsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户关注
 */
@RestController
@RequestMapping("follow")
public class FollowerController {

    @Autowired
    private IUserFollowsService iUserFollowsService;

    /**
     * 关注/取消关注
     * @param followId
     * @return
     */
    @RequestMapping(value = "/doFollow")
    public Map<String,String> doFollow(Integer followId) {
        //获取当前用户
        Integer curUserId = SessionContext.getUserId();
        UserFollows userFollows = new UserFollows();
        userFollows.setUserId(curUserId);
        userFollows.setFollowId(followId);
        Map<String, String> map = new HashMap<>();
        List<UserFollows> list = iUserFollowsService.queryALl(curUserId, followId);
        if (CollectionUtils.isNotEmpty(list)) {
            //如果有，就删除
            iUserFollowsService.delete(list.get(0).getId());
            map.put("errcode", "0");
        } else {
            //创建
            iUserFollowsService.insert(userFollows);
            map.put("errcode", "1");
        }
        return map;
    }

    @RequestMapping(value = "/isFollow")
    public Map<String,String> isFollow(Integer followId) {
        Map<String, String> map = new HashMap<>();
        //获取当前用户
        Integer curUserId = SessionContext.getUserId();
        List<UserFollows> list = iUserFollowsService.queryALl(curUserId, followId);
        if (CollectionUtils.isNotEmpty(list)) {
            map.put("errcode", "1");
        }else {
            map.put("errcode", "0");
        }
        return map;
    }

}
