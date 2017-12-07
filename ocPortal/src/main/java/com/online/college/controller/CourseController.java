package com.online.college.controller;


import com.online.college.business.ICourseBusiness;
import com.online.college.common.web.SessionContext;
import com.online.college.ov.CourseSectionVO;
import com.online.college.pojo.AuthUser;
import com.online.college.pojo.Course;
import com.online.college.pojo.CourseSection;
import com.online.college.pojo.UserCourseSection;
import com.online.college.service.IAuthUserService;
import com.online.college.service.ICourseSectionService;
import com.online.college.service.ICourseService;
import com.online.college.service.IUserCourseSectionService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 课程详情信息
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private ICourseBusiness iCourseBusiness;

    @Autowired
    private IAuthUserService iAuthUserService;

    @Autowired
    private IUserCourseSectionService iUserCourseSectionService;

    @Autowired
    private ICourseSectionService iCourseSectionService;
    /**
     * 课程章节页面
     * @return
     */
    @GetMapping("/learn/{courseId}")
    public ModelAndView learn(@PathVariable Integer courseId) {
        if(null == courseId)
            return new ModelAndView("error/404");

        //获取课程
        Course course = iCourseService.getById(courseId);
        if(null == course)
            return new ModelAndView("error/404");

        ModelAndView mv = new ModelAndView("learn");
        //获取课程章节
        List<CourseSectionVO> chaptSections = iCourseBusiness.queryCourseSection(courseId);
        mv.addObject("course", course);
        mv.addObject("chaptSections", chaptSections);
        //获取讲师
        AuthUser courseTeacher = iAuthUserService.getByUsername(course.getUsername());
        mv.addObject("courseTeacher", courseTeacher);
        //获取推荐课程
        List<Course> recomdCourseList = iCourseService.queryCourseBySubClassify(course.getSubClassify(), 1, 5);
        mv.addObject("recomdCourseList", recomdCourseList);
        //当前学习章节
        UserCourseSection userCourseSection = iUserCourseSectionService.queryLatest(courseId, SessionContext.getUserId(), null);
        if (userCourseSection != null) {
            //获取课程章节
            CourseSection curCourseSection = iCourseSectionService.getById(userCourseSection.getSectionId());
            mv.addObject("curCourseSection", curCourseSection);
        }
        return mv;
    }
}
