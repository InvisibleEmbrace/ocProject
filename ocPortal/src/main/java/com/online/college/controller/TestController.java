package com.online.college.controller;

import com.online.college.dao.CourseMapper;
import com.online.college.pojo.Course;
import com.online.college.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ICourseService iCourseService;


}
