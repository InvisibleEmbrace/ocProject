package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.common.web.SessionContext;
import com.online.college.service.ICourseService;
import com.online.college.service.IUserCollectionsService;
import com.online.college.service.IUserFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserFollowsService iUserFollowsService;

    @Autowired
    private IUserCollectionsService iUserCollectionsService;

    @Autowired
    private ICourseService iCourseService;

    /**
     * 首页
     */
    @RequestMapping("/home")
    public ModelAndView index(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        ModelAndView mv = new ModelAndView("user/home");
        mv.addObject("curNav", "home");
        //加载关注用户的动态
        PageInfo page = iUserFollowsService.queryFollowStudyRecord(SessionContext.getUserId(), pageNum, pageSize);
        //处理头像
        mv.addObject("page", page);
        return mv;
    }

    /**
     * 我的课程
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/course")
    public ModelAndView course(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        ModelAndView mv = new ModelAndView("user/course");
        mv.addObject("curNav", "course");
        PageInfo page = iCourseService.queryMyCourse(SessionContext.getUserId(), pageNum, pageSize);
        mv.addObject("page", page);
        return mv;
    }

    /**
     * 我的收藏
     */
    @RequestMapping("/collect")
    public ModelAndView collect(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ModelAndView mv = new ModelAndView("user/collect");
        mv.addObject("curNav", "collect");
        PageInfo page = iUserCollectionsService.queryMyCollection(SessionContext.getUserId(), pageNum, pageSize);
        mv.addObject("page", page);
        return mv;
    }
}
