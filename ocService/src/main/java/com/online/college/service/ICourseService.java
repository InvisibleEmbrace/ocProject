package com.online.college.service;

import com.github.pagehelper.PageInfo;
import com.online.college.pojo.Course;

import java.util.List;

/**
 * 课程服务接口
 */
public interface ICourseService {

    /**
     * 查询推荐课程
     * @return
     */
    List<Course> prepareRecomdCourses(String classify);

    /**
     * 查询推荐实战和免费或者免费课程
     */
    List<Course> queryCourse(int free, int pageNum, int pageSize);

    /**
     * 根据权重（学习数量studyCount）进行排序
     * @param courseType
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Course> queryCourseByType(String courseType, int pageNum, int pageSize);

    /**
     * 根据一二级分类获取课程
     * @param curCode
     * @param curSubCode
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Course> queryCourseByClassify(String curCode, String curSubCode, Integer pageNum, Integer pageSize, String sort);

    /**
     * 根据id获取课程
     * @param id
     * @return
     */
    Course getById(Integer id);

    /**
     * 根据二级分类推荐课程
     *
     * @param subClassify
     * @return
     */
    List<Course> queryCourseBySubClassify(String subClassify,Integer pageNum,Integer pageSize);

    /**
     * 获取我的课程
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo queryMyCourse(Integer userId, Integer pageNum, Integer pageSize);
}
