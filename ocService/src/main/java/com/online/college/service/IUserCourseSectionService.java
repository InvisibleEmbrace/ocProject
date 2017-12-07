package com.online.college.service;

import com.online.college.pojo.UserCourseSection;

public interface IUserCourseSectionService {

    /**
     * 根据课程id，用户id获取学习记录
     *
     * @param courseId
     * @param userId
     * @return
     */
    UserCourseSection queryLatest(Integer courseId, Integer userId, Integer sectionId);
}
