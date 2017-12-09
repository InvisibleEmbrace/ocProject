package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.common.web.SessionContext;
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

    /**
     * 首页
     */
    @RequestMapping("/home")
    public ModelAndView index(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ModelAndView mv = new ModelAndView("user/home");
        mv.addObject("curNav", "home");
        //加载关注用户的动态
        PageInfo page = iUserFollowsService.queryFollowStudyRecord(SessionContext.getUserId(), pageNum, pageSize);
        //处理头像
        mv.addObject("page", page);
        return mv;
    }
}
