package com.online.college.web;

import com.online.college.common.util.JsonUtil;
import com.online.college.common.web.HttpHelper;
import com.online.college.common.web.JsonView;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * shiro 对用户是否登录的 filter
 */
public class AuthFilter extends FormAuthenticationFilter {

    private static final Integer SHIRO_TIME_OUT = 1001;

    /**
     * isAccessAllowed：即是否允许访问，返回 true 表示允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * onAccessDenied：表示访问拒绝时是否自己处理，如果返回 true 表示自己不处理且继续拦截器链执行，返回 false 表示自己已经处理了（比如重定向到另一个页面）。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取请求路径
        String path = httpServletRequest.getContextPath();
        //判断请求是否为登录页，如果是则放行
        if (path.equals("/index.html")) {
            return true;
        }
        //获取当前用户
        Subject subject = getSubject(request, response);
        //判断是否授权
        if (subject.isAuthenticated()) {
            return true;
        }
        //判断是否是ajax请求
        if (HttpHelper.isAjaxRequest(httpServletRequest)) {
            JsonView jv = new JsonView();
            jv.setMessage("SHIRO登录超时");
            jv.setErrcode(SHIRO_TIME_OUT);
            HttpServletResponse _response = (HttpServletResponse) response;
            PrintWriter pw = _response.getWriter();
            _response.setContentType("application/json");
            pw.write(JsonUtil.toJson(jv));
            pw.flush();
            pw.close();
        } else {
            saveRequestAndRedirectToLogin(request, response);
        }
        // 如果没有授权则跳转到登录页面
        return false;
    }
}
