package com.online.college.vo;

import com.online.college.pojo.ConstsClassify;
import com.online.college.pojo.Course;

import java.util.ArrayList;
import java.util.List;


/**
 * 页面展示 value object
 */
public class ConstsClassifyVO extends ConstsClassify {
	

	//子分类列表
	private List<ConstsClassify> subClassifyList = new ArrayList<ConstsClassify>();

	//课程推荐列表
	private List<Course> recomdCourseList ;
	
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
