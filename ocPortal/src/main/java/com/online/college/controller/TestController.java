package com.online.college.controller;

import com.online.college.business.ICourseBusiness;
import com.online.college.dao.UserFollowsMapper;
import com.online.college.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private ICourseBusiness iCourseBusiness;

    @Autowired
    private UserFollowsMapper userFollowsMapper;

    @GetMapping("/test")
    public int test() {
        /*List<UserFollowStudyRecord> userFollowStudyRecords = userFollowsMapper.queryFollowStudyRecord(1);*/
        int followStudyRecordCount = userFollowsMapper.getFollowStudyRecordCount(1);
        return followStudyRecordCount;
    }


}
