package com.online.college.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.college.dao.CourseMapper;
import com.online.college.pojo.Course;
import com.online.college.service.ICourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * 课程服务接口实现
 */
@Service("iCourseService")
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> prepareRecomdCourses(String classify) {
        List<Course> courseList = courseMapper.prepareRecomdCourses(classify);
        return courseList;
    }

    @Override
    public List<Course> queryCourse(int free, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courseList = courseMapper.queryCourse(free);
        return courseList;
    }

    @Override
    public List<Course> queryCourseByType(String courseType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courseList = courseMapper.queryCourseByType(courseType);
        return courseList;
    }

    @Override
    public PageInfo<Course> queryCourseByClassify(String curCode, String curSubCode, Integer pageNum, Integer pageSize, String sort) {
        if (pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        if (sort != null) {
            if ("pop".equals(sort)) {
                PageHelper.orderBy("study_count DESC");
            } else {
                PageHelper.orderBy("id DESC");
            }
        }
        List<Course> courseList = courseMapper.queryCourseByClassify(curCode, curSubCode);
        PageInfo<Course> coursePageInfo = new PageInfo<>(courseList);
        return coursePageInfo;
    }

    @Override
    public Course getById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Course> queryCourseBySubClassify(String subClassify, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        return courseMapper.queryCourseBySubClassify(subClassify);
    }
}
