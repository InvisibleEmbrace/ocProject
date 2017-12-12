package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.common.web.JsonView;
import com.online.college.pojo.AuthUser;
import com.online.college.service.IAuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class AuthUserController {

    @Autowired
    private IAuthUserService iAuthUserService;

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Integer id){
        AuthUser user = iAuthUserService.getById(id);
        return JsonView.render(user);
    }

    /**
     * 分页
     */
    @RequestMapping(value = "/userPageList")
    public ModelAndView queryPage(AuthUser queryEntity , @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        ModelAndView mv = new ModelAndView("cms/user/userPageList");
        mv.addObject("curNav", "user");

        if(StringUtils.isNotEmpty(queryEntity.getUsername())){
            queryEntity.setUsername(queryEntity.getUsername().trim());
        }else{
            queryEntity.setUsername(null);
        }

        if(Integer.valueOf(-1).equals(queryEntity.getStatus())){
            queryEntity.setStatus(null);
        }

        PageInfo page = iAuthUserService.queryPage(queryEntity.getUsername(), queryEntity.getStatus(), pageNum, pageSize);
        mv.addObject("page",page);
        mv.addObject("queryEntity",queryEntity);

        return mv;
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(AuthUser entity){
        entity.setUsername(null);//不更新
        entity.setRealname(null);//不更新
        iAuthUserService.update(entity);
        return new JsonView(0).toString();
    }


}
