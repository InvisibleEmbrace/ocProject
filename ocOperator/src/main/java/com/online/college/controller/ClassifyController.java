package com.online.college.controller;

import com.online.college.business.IPortalBusiness;
import com.online.college.common.web.JsonView;
import com.online.college.pojo.ConstsClassify;
import com.online.college.service.IConstsClassifyService;
import com.online.college.vo.ConstsClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private IConstsClassifyService iConstsClassifySrvice;

    @Autowired
    private IPortalBusiness iPortalBusiness;


    @RequestMapping(value = "/getById")
    public String getById(Integer id){
        return JsonView.render(iConstsClassifySrvice.getById(id));
    }

    @RequestMapping(value = "/index")
    public ModelAndView classifyIndex(){
        ModelAndView mv = new ModelAndView("cms/classify/classifyIndex");
        mv.addObject("curNav", "classify");
        //所有一级分类
        Map<String,ConstsClassifyVO> classifyMap = iPortalBusiness.queryAllClassifyMap();

        //所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            classifysList.add(vo);
        }
        mv.addObject("classifys", classifysList);

        List<ConstsClassify> subClassifys = new ArrayList<>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            subClassifys.addAll(vo.getSubClassifyList());
        }
        mv.addObject("subClassifys", subClassifys);//所有二级分类
        return mv;
    }


    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(ConstsClassify entity){
        if(entity.getId() == null){
            ConstsClassify tmpEntity = iConstsClassifySrvice.getByCode(entity.getCode());
            if(tmpEntity != null){
                return JsonView.render(1, "此编码已存在");
            }
            iConstsClassifySrvice.insert(entity);
        }else{
            iConstsClassifySrvice.insert(entity);
        }
        return new JsonView().toString();
    }

    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(ConstsClassify entity){
        iConstsClassifySrvice.delete(entity.getId());
        return new JsonView().toString();
    }

}
