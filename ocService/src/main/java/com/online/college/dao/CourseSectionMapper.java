package com.online.college.dao;

import com.online.college.pojo.CourseSection;

import java.util.List;

public interface CourseSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseSection record);

    int insertSelective(CourseSection record);

    CourseSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseSection record);

    int updateByPrimaryKey(CourseSection record);

    List<CourseSection> queryAll(Integer courseId);
}