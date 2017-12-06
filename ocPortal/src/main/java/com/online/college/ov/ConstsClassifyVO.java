package com.online.college.ov;

import com.online.college.pojo.ConstsClassify;
import com.online.college.pojo.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面vo
 */
public class ConstsClassifyVO extends ConstsClassify {

    //子分类列表
    private List<ConstsClassify> subClassifyList = new ArrayList<>();

    //课程推荐列表
    private List<Course> recomdCourseList;

    public List<ConstsClassify> getSubClassifyList() {
        return subClassifyList;
    }

    public void setSubClassifyList(List<ConstsClassify> subClassifyList) {
        this.subClassifyList = subClassifyList;
    }

    public List<Course> getRecomdCourseList() {
        return recomdCourseList;
    }

    public void setRecomdCourseList(List<Course> recomdCourseList) {
        this.recomdCourseList = recomdCourseList;
    }

}
