package com.online.college.vo;

import com.online.college.pojo.CourseComment;

public class CourseCommentVO extends CourseComment {

    /**
     * 用户头像
     */
    private String header;

    /**
     * 课程名称
     */
    private String courseName;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
