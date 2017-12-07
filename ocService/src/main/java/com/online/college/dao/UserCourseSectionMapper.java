package com.online.college.dao;

import com.online.college.pojo.UserCourseSection;
import org.apache.ibatis.annotations.Param;

public interface UserCourseSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCourseSection record);

    int insertSelective(UserCourseSection record);

    UserCourseSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCourseSection record);

    int updateByPrimaryKey(UserCourseSection record);

    UserCourseSection queryLatest(@Param("courseId") Integer courseId, @Param("userId") Integer userId,@Param("sectionId") Integer sectionId);
}