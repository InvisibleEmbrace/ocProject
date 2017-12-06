package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.business.IPortalBusiness;
import com.online.college.ov.ConstsClassifyVO;
import com.online.college.pojo.ConstsClassify;
import com.online.college.pojo.Course;
import com.online.college.service.IConstsClassifyService;
import com.online.college.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 课程分类页
 */
@RestController
@RequestMapping("/course")
public class CourseListController {

    @Autowired
    private IPortalBusiness iPortalBusiness;

    @Autowired
    private IConstsClassifyService iConstsClassifyService;

    @Autowired
    private ICourseService iCourseService;

    @GetMapping("/list")
    public ModelAndView list(String c,@RequestParam(value = "sort",required = false) String sort, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView("list");
        String curCode = "-1";//当前方向code
        String curSubCode = "-2";//当前分类code

        //加载所有课程分类
        Map<String, ConstsClassifyVO> voMap = iPortalBusiness.queryAllClassifyMap();
        //获取所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<>();
        for (ConstsClassifyVO vo : voMap.values()
                ) {
            classifysList.add(vo);
        }
        modelAndView.addObject("classifys", classifysList);
        //当前分类
        ConstsClassify curClassify = iConstsClassifyService.getByCode(c);

        if (curClassify == null) { //没有此分类，加载所有二级分类
            List<ConstsClassify> subClassifys = new ArrayList<>();
            for (ConstsClassifyVO vo : voMap.values()
                    ) {
                subClassifys.addAll(vo.getSubClassifyList());
            }
        } else {
            if ("0".equals(curClassify.getParentCode())) { //当前是一级分类
                curCode = curClassify.getCode();
                modelAndView.addObject("subClassifys", voMap.get(curClassify.getCode()).getSubClassifyList());//此分类下的二级分类
            }else { //当前是二级分类
                curSubCode = curClassify.getCode();
                curCode = curClassify.getParentCode();
                modelAndView.addObject("subClassifys", voMap.get(curClassify.getParentCode()).getSubClassifyList());
            }
        }
        modelAndView.addObject("curCode", curCode);
        modelAndView.addObject("curSubCode", curSubCode);
        PageInfo<Course> courseList = iCourseService.queryCourseByClassify(curCode, curSubCode, pageNum, pageSize, sort);
        modelAndView.addObject("courseList", courseList);
        return modelAndView;
    }

}
