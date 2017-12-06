package com.online.college.controller;

import com.online.college.business.IPortalBusiness;
import com.online.college.dao.AuthUserMapper;
import com.online.college.dao.CourseMapper;
import com.online.college.ov.ConstsClassifyVO;
import com.online.college.pojo.AuthUser;
import com.online.college.pojo.ConstsClassify;
import com.online.college.pojo.ConstsSiteCarousel;

import com.online.college.pojo.Course;
import com.online.college.service.IAuthUserService;
import com.online.college.service.IConstsSiteCarouselService;
import com.online.college.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProtalController {


    @Autowired
    private IConstsSiteCarouselService iConstsSiteCarouselService;

    @Autowired
    private IPortalBusiness iPortalBusiness;

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private IAuthUserService iAuthUserService;
    


    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        //加载轮播图
        List<ConstsSiteCarousel> carouselList = iConstsSiteCarouselService.queryCarousel(4);
        modelAndView.addObject("carouselList", carouselList);
        //课程一级分类
        List<ConstsClassifyVO> constsClassifyVOList = iPortalBusiness.queryAllClassify();
        //课程推荐
        iPortalBusiness.prepareRecomdCourses(constsClassifyVOList);
        modelAndView.addObject("constsClassifyVOList", constsClassifyVOList);
        //获取5门实战课推荐，根据权重（weight）进行排序
        List<Course> actionCourseList = iCourseService.queryCourse(0, 1, 5);
        modelAndView.addObject("actionCourseList", actionCourseList);
        //获取5门免费课推荐，根据权重（weight）进行排序
        List<Course> freeCourseList = iCourseService.queryCourse(1, 1, 5);
        modelAndView.addObject("freeCourseList", freeCourseList);
        //获取7门java课程，根据权重（学习数量studyCount）进行排序
        List<Course> javaCourseList = iCourseService.queryCourseByType("java", 1, 7);
        modelAndView.addObject("javaCourseList", javaCourseList);
        //加载讲师
        List<AuthUser> recomdTeacherList = iAuthUserService.queryRecomd(1, 5);
        modelAndView.addObject("recomdTeacherList", recomdTeacherList);
        return modelAndView;
    }

    @GetMapping("/need")
    public List<Course> need() {
        List<Course> freeCourseList = iCourseService.queryCourse(1, 1, 5);
        return freeCourseList;
    }

}
