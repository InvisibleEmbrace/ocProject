package com.online.college.controller;


import com.online.college.business.ICourseBusiness;
import com.online.college.common.web.JsonView;
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
import net.sf.json.JSONObject;
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
     *
     * @return
     */
    @GetMapping("/learn/{courseId}")
    public ModelAndView learn(@PathVariable Integer courseId) {
        if (null == courseId)
            return new ModelAndView("error/404");

        //获取课程
        Course course = iCourseService.getById(courseId);
        if (null == course)
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

    /**
     * 视频学习页面
     *
     * @return
     */
    @RequestMapping("/video/{sectionId}")
    public ModelAndView video(@PathVariable Integer sectionId) {
        ModelAndView mv = new ModelAndView("video");
        if (null == sectionId)
            return new ModelAndView("error/404");
        CourseSection courseSection = iCourseSectionService.getById(sectionId);
        if (null == courseSection)
            return new ModelAndView("error/404");
        //获取课程章节
        List<CourseSectionVO> chaptSections = iCourseBusiness.queryCourseSection(courseSection.getCourseId());
        mv.addObject("courseSection", courseSection);
        mv.addObject("chaptSections", chaptSections);
        //学习记录
        UserCourseSection userCourseSection = iUserCourseSectionService.queryLatest(courseSection.getCourseId(), SessionContext.getUserId(), sectionId);
        if (userCourseSection == null) {
            //如果没有则插入
            UserCourseSection ucs = new UserCourseSection();
            ucs.setCourseId(courseSection.getCourseId());
            ucs.setUserId(SessionContext.getUserId());
            ucs.setSectionId(sectionId);
            ucs.setCreateUser(SessionContext.getUsername());
            ucs.setUpdateUser(SessionContext.getUsername());
            //插入
            iUserCourseSectionService.insert(ucs);
        } else {
            //更新
            iUserCourseSectionService.updata(userCourseSection);
        }
        return mv;
    }

    @RequestMapping("/getCurLeanInfo")
    public String getCurLeanInfo(){
        JsonView jv = new JsonView();
        //加载当前用户学习最新课程
        if(SessionContext.isLogin()){
            UserCourseSection userCourseSection = new UserCourseSection();
            userCourseSection.setUserId(SessionContext.getUserId());
            userCourseSection = iUserCourseSectionService.queryLatest(null, SessionContext.getUserId(), null);
            if(null != userCourseSection){
                JSONObject jsObj = new JSONObject();
                CourseSection curCourseSection = iCourseSectionService.getById(userCourseSection.getSectionId());
                jsObj.put("curCourseSection", curCourseSection);
                Course curCourse = iCourseService.getById(userCourseSection.getCourseId());
                jsObj.put("curCourse", curCourse);
                jv.setData(jsObj);
            }
        }
        return jv.toString();
    }
}
