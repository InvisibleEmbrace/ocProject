package com.online.college.dao;

import com.online.college.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKeyWithBLOBs(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> prepareRecomdCourses(String code);

    List<Course> queryCourse(int free);

    List<Course> queryCourseByType(String courseType);

    List<Course> queryCourseByClassify(@Param("curCode") String curCode, @Param("curSubCode") String curSubCode);

    List<Course> queryCourseBySubClassify(String subClassify);
}