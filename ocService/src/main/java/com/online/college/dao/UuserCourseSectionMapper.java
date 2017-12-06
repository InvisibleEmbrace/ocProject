package com.online.college.dao;

import com.online.college.pojo.UuserCourseSection;

public interface UuserCourseSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UuserCourseSection record);

    int insertSelective(UuserCourseSection record);

    UuserCourseSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UuserCourseSection record);

    int updateByPrimaryKey(UuserCourseSection record);
}