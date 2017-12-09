package com.online.college.controller;

import com.online.college.common.util.EncryptUtil;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.pojo.AuthUser;
import com.online.college.service.IAuthUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 注册页面
     */
    @RequestMapping(value = "/register")
    public ModelAndView register(){
        if(SessionContext.isLogin()){
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/register");
    }

    /**
     * 实现注册
     * @param authUser
     * @param identiryCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/doRegister")
    public String doRegister(AuthUser authUser, String identiryCode, HttpServletRequest request) {
        //验证码判断
        if (identiryCode != null && identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2);
        }
        AuthUser tempUser = iAuthUserService.getByUsername(authUser.getUsername());
        if (tempUser != null) {
            return JsonView.render(1);
        }else {
            authUser.setPassword(EncryptUtil.encodedByMD5(authUser.getPassword()));
            iAuthUserService.insert(authUser);
            return JsonView.render(0);
        }
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "/login")
    public  ModelAndView login(){
        if(SessionContext.isLogin()){
            return new ModelAndView("redirect:/index.html");
        }
        return new ModelAndView("auth/login");
    }

    /**
     * ajax登录
     */
    @RequestMapping(value = "/ajaxlogin")
    public String ajaxLogin(AuthUser user, String identiryCode, Integer rememberMe, HttpServletRequest request) {
        //验证码判断
        if (identiryCode != null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))) {
            return JsonView.render(2, "验证码不正确!");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            if (rememberMe != null && rememberMe == 1) {
                token.setRememberMe(true);
            }
            currentUser.login(token);//不抛出异常登录成功
            return new JsonView().toString();
        } catch (AuthenticationException e) { //登录失败
            return JsonView.render(1, "用户名密码不正确！");
        }
    }

    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(AuthUser user, String identiryCode, HttpServletRequest request, HttpServletResponse response){
        //如果已经登录过
        if (SessionContext.getAuthUser() != null) {
            return new ModelAndView("redirect:/user/home.html");
        }
        //验证码判断
        if(identiryCode!=null && !identiryCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 1);
            return mv;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),EncryptUtil.encodedByMD5(user.getPassword()));
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);//shiro实现登录
           /* String url = "http://localhost:8080/user/home.html";
            HttpHelper.redirectHttpUrl(request, response, url);*/
            return new ModelAndView("redirect:/user/home.html");
        }catch(AuthenticationException e){ //登录失败
            ModelAndView mv = new ModelAndView("auth/login");
            mv.addObject("errcode", 2);
            return mv;
        }
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        SessionContext.shiroLogout();
        return new ModelAndView("redirect:/index.html");
    }
}
