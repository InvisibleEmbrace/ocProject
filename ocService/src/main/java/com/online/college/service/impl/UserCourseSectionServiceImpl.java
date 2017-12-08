package com.online.college.service.impl;

import com.online.college.dao.UserCourseSectionMapper;
import com.online.college.pojo.UserCollections;
import com.online.college.pojo.UserCourseSection;
import com.online.college.service.IUserCourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCourseSectionServiceImpl implements IUserCourseSectionService {

    @Autowired
    private UserCourseSectionMapper userCourseSectionMapper;

    @Override
    public UserCourseSection queryLatest(Integer courseId, Integer userId, Integer sectionId) {
        return userCourseSectionMapper.queryLatest(courseId, userId, sectionId);
    }

    @Override
    public void insert(UserCourseSection userCourseSection) {
        userCourseSectionMapper.insertSelective(userCourseSection);
    }

    @Override
    public void updata(UserCourseSection userCourseSection) {
        userCourseSectionMapper.updateByPrimaryKeySelective(userCourseSection);
    }
}
