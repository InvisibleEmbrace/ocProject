package com.online.college.business;

import com.online.college.ov.CourseSectionVO;

import java.util.List;

public interface ICourseBusiness {

    /**
     * 根据课程id获取课程章节
     *
     * @param courseId
     * @return
     */
    List<CourseSectionVO> queryCourseSection(Integer courseId);
}
