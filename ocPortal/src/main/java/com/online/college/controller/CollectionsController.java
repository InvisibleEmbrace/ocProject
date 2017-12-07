package com.online.college.controller;

import com.online.college.common.constant.CourseEnum;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.pojo.UserCollections;
import com.online.college.service.IUserCollectionsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    private IUserCollectionsService iUserCollectionsService;

    /**
     * 收藏/取消收藏
     * @param courseId
     * @return
     */
    @RequestMapping("/doCollection")
    @ResponseBody
    public Map<String,String> doCollection(Integer courseId) {
        //获取当前用户
        Integer curUserId = SessionContext.getUserId();
        UserCollections userCollections = new UserCollections();
        userCollections.setUserId(curUserId);
        userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());
        userCollections.setObjectId(courseId);
        Map<String, String> map = new HashMap<>();
        List<UserCollections> list = iUserCollectionsService.getCollection(curUserId, CourseEnum.COLLECTION_CLASSIFY_COURSE.value(), courseId);
        if (CollectionUtils.isNotEmpty(list)) {
            iUserCollectionsService.delete(list.get(0));
            map.put("errcode", "0");
            return map;
        } else {
            iUserCollectionsService.insert(userCollections);
            map.put("errcode", "1");//已经收藏
        }
        return map;
    }

    /**
     * 是否已经收藏
     * @param courseId
     * @return
     */
    @RequestMapping("/isCollection")
    @ResponseBody
    public Map<String,String> isCollection(Integer courseId){
        //获取当前用户
        Integer curUserId = SessionContext.getUserId();
        Map<String, String> map = new HashMap<>();
        List<UserCollections> list = iUserCollectionsService.getCollection(curUserId, CourseEnum.COLLECTION_CLASSIFY_COURSE.value(), courseId);
        if(CollectionUtils.isNotEmpty(list)){//已经收藏
            map.put("errcode", "1");
            return map;
        }else{
            map.put("errcode", "0");
            return map;
        }
    }
}
