package com.online.college.service.impl;

import com.online.college.dao.CourseSectionMapper;
import com.online.college.pojo.Course;
import com.online.college.pojo.CourseSection;
import com.online.college.service.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iCourseSectionService")
public class CourseSectionServiceImpl implements ICourseSectionService {

    @Autowired
    private CourseSectionMapper courseSectionMapper;

    @Override
    public List<CourseSection> queryAll(Integer courseId) {
        List<CourseSection> courseSections = courseSectionMapper.queryAll(courseId);
        return courseSections;
    }

    @Override
    public CourseSection getById(Integer id) {
        return courseSectionMapper.selectByPrimaryKey(id);
    }

}
