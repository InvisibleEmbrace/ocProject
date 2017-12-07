package com.online.college.dao;

import com.online.college.pojo.CourseComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseComment record);

    int insertSelective(CourseComment record);

    CourseComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseComment record);

    int updateByPrimaryKey(CourseComment record);

    List<CourseComment> queryList(@Param("courseId") Integer courseId, @Param("type") Integer type);
}