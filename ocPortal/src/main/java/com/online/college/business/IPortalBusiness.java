package com.online.college.business;

import com.github.pagehelper.PageInfo;
import com.online.college.ov.ConstsClassifyVO;

import java.util.List;
import java.util.Map;

public interface IPortalBusiness {
    /**
     * 获取所有，包括一级分类&二级分类
     */
    List<ConstsClassifyVO> queryAllClassify();

    /**
     * 获取所有分类
     */
    Map<String,ConstsClassifyVO> queryAllClassifyMap();

    /**
     * 为分类设置课程推荐
     */
    void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList);

}
