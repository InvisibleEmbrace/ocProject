package com.online.college.service;

import com.online.college.pojo.CourseSection;

import java.util.List;

public interface ICourseSectionService {

    List<CourseSection> queryAll(Integer courseId);

    /**
     * 根据id获取章节
     * @param id
     * @return
     */
    CourseSection getById(Integer id);
}
