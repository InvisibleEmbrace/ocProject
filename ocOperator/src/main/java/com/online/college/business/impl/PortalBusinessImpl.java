package com.online.college.business.impl;

import com.github.pagehelper.PageHelper;
import com.online.college.business.IPortalBusiness;
import com.online.college.dao.CourseMapper;
import com.online.college.pojo.ConstsClassify;
import com.online.college.pojo.Course;
import com.online.college.service.IConstsClassifyService;
import com.online.college.service.ICourseService;
import com.online.college.vo.ConstsClassifyVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("iPortalBusiness")
public class PortalBusinessImpl implements IPortalBusiness {


    @Autowired
    private IConstsClassifyService iConstsClassifyService;

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 获取所有，包括一级分类&二级分类
     */
    @Override
    public List<ConstsClassifyVO> queryAllClassify() {
        List<ConstsClassifyVO> resultList = new ArrayList<>();
        for(ConstsClassifyVO vo : this.queryAllClassifyMap().values()){
            resultList.add(vo);
        }
        return resultList;
    }

    /**
     * 获取所有分类
     */
    @Override
    public Map<String, ConstsClassifyVO> queryAllClassifyMap() {
        Map<String, ConstsClassifyVO> resultMap = new LinkedHashMap<>();
        //获取迭代器
        Iterator<ConstsClassify> iterator = iConstsClassifyService.queryAll().iterator();
        while(iterator.hasNext()) {
            ConstsClassify classify = iterator.next();
            //一级分类
            if ("0".equals(classify.getParentCode())) {
                ConstsClassifyVO constsClassifyVO = new ConstsClassifyVO();
                BeanUtils.copyProperties(classify, constsClassifyVO);
                resultMap.put(classify.getCode(), constsClassifyVO);
            }else { //二级分类
                if (resultMap.get(classify.getParentCode()) != null) {
                    //添加到子类中
                    resultMap.get(classify.getParentCode()).getSubClassifyList().add(classify);
                }
            }
        }
        return resultMap;
    }

    /**
     * 为分类设置课程推荐
     */
    @Override
    public void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList) {
        if (CollectionUtils.isNotEmpty(classifyVoList)) {
            for (ConstsClassifyVO vo : classifyVoList
                    ) {
                PageHelper.startPage(1, 8);
                List<Course> courseList = iCourseService.prepareRecomdCourses(vo.getCode());
                if (CollectionUtils.isNotEmpty(courseList)) {
                    vo.setRecomdCourseList(courseList);
                }
            }
        }
    }
}
