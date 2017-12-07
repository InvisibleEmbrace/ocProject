package com.online.college.service;

import java.util.List;
/**
 * /**
 * 课程评论&答疑
 */

import com.github.pagehelper.PageInfo;
import com.online.college.pojo.CourseComment;

public interface ICourseCommentService {

    //获取所有评论和问答
    PageInfo  queryList(Integer courseId, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 根据id获取评论
     * @param id
     * @return
     */
    CourseComment getById(Integer id);

    /**
     * 插入评论
     * @param courseComment
     */
    void insertCourseComment(CourseComment courseComment);

}
