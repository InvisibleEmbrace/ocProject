package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.common.web.JsonView;
import com.online.college.pojo.ConstsCollege;
import com.online.college.service.IConstsCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 网校管理
 */
@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private IConstsCollegeService iConstsCollegeService;

    /**
     * 分页
     * @param queryEntity
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryPageList")
    public ModelAndView queryPage(ConstsCollege queryEntity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ModelAndView mv = new ModelAndView("cms/college/collegePageList");
        mv.addObject("curNav", "college");
        PageInfo page = iConstsCollegeService.queryPage(queryEntity.getName(), pageNum, pageSize);
        mv.addObject("page",page);
        mv.addObject("queryEntity",queryEntity);
        return mv;
    }

    @RequestMapping(value = "/getById")
    public String getById(Integer id){
        return JsonView.render(iConstsCollegeService.getById(id));
    }

    @RequestMapping(value = "/doMerge")
    public String doMerge(ConstsCollege entity) {
        if (entity.getId() == null) {
            ConstsCollege tmpEntity = iConstsCollegeService.getByCode(entity.getCode());
            if (tmpEntity != null) {
                return JsonView.render(1, "此编码已存在");
            }
            iConstsCollegeService.insert(entity);
        } else {
            ConstsCollege tmpEntity = iConstsCollegeService.getByCode(entity.getCode());
            if(tmpEntity != null && !tmpEntity.getId().equals(entity.getId())){
                return JsonView.render(1, "此编码已存在");
            }
            iConstsCollegeService.update(entity);
        }
        return new JsonView().toString();
    }

    @RequestMapping(value = "/deleteLogic")
    public String deleteLogic(ConstsCollege entity){
        iConstsCollegeService.delete(entity.getId());
        return new JsonView().toString();
    }
}
