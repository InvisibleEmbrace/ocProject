package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.pojo.AuthUser;
import com.online.college.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserFollowsService iUserFollowsService;

    @Autowired
    private IUserCollectionsService iUserCollectionsService;

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private IAuthUserService iAuthUserService;

    @Autowired
    private ICourseCommentService iCourseCommentService;


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

    /**
     * 信息
     */
    @RequestMapping("/info")
    public ModelAndView info(){
        ModelAndView mv = new ModelAndView("user/info");
        mv.addObject("curNav","info");
        AuthUser authUser = iAuthUserService.getById(SessionContext.getUserId());
        if (authUser != null && StringUtils.isNotBlank(authUser.getHeader())) {
            authUser.setHeader(QiniuStorage.getUrl(authUser.getHeader()));
        }
        mv.addObject("authUser",authUser);
        return mv;
    }

    /**
     * 保存信息
     */
    @RequestMapping("/saveInfo")
    public String saveInfo(AuthUser authUser,  @RequestParam MultipartFile pictureImg) {
        authUser.setId(SessionContext.getUserId());
        try {
            if (pictureImg != null && pictureImg.getBytes().length > 0) {
                String key = QiniuStorage.uploadImage(pictureImg.getBytes());
                authUser.setHeader(key);
            }
            iAuthUserService.update(authUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonView().toString();
    }


    /**
     * 密码
     */
    @RequestMapping("/passwd")
    public ModelAndView passwd(){
        ModelAndView mv = new ModelAndView("user/passwd");
        mv.addObject("curNav","passwd");
        return mv;
    }

    @RequestMapping("/savePasswd")
    public String savePasswd(String oldPassword, String password, String rePassword){
        AuthUser curUser = iAuthUserService.getById(SessionContext.getUserId());
        if (curUser == null) {
            return JsonView.render(1,"用户不存在！");
        }
        oldPassword = EncryptUtil.encodedByMD5(oldPassword.trim());
        if (!oldPassword.equals(curUser.getPassword())) {
            return JsonView.render(1,"旧密码不正确！");
        }
        if (StringUtils.isBlank(password.trim())) {
            return JsonView.render(1,"新密码不能为空！");
        }
        curUser.setPassword(EncryptUtil.encodedByMD5(password));
        iAuthUserService.update(curUser);
        return new JsonView().toString();
    }

    /**
     * 问答
     */
    @RequestMapping("/qa")
    public ModelAndView qa(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        ModelAndView mv = new ModelAndView("user/qa");
        mv.addObject("curNav","qa");
        PageInfo page = iCourseCommentService.querCommentByUsername(SessionContext.getUsername(), pageNum, pageSize);
        mv.addObject("page", page);
        return mv;
    }

}
