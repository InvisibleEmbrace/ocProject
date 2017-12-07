package com.online.college.business.impl;

import com.online.college.business.ICourseBusiness;
import com.online.college.ov.CourseSectionVO;
import com.online.college.pojo.CourseSection;
import com.online.college.service.ICourseSectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@Service("iCourseBusiness")
public class CourseBusinessImpl implements ICourseBusiness {

    @Autowired
    private ICourseSectionService iCourseSectionService;

    /**
     * 获取课程章节
     */
    public List<CourseSectionVO> queryCourseSection(Integer courseId) {
        List<CourseSectionVO> resultList = new ArrayList<>();
        LinkedHashMap<Integer, CourseSectionVO> map = new LinkedHashMap<>();
        Iterator<CourseSection> iterator = iCourseSectionService.queryAll(courseId).iterator();
        while (iterator.hasNext()) {
            CourseSection section = iterator.next();
            if (section.getParentId() == 0) {
                CourseSectionVO vo = new CourseSectionVO();
                BeanUtils.copyProperties(section, vo);
                map.put(section.getId(), vo);
            }else {
                map.get(section.getParentId()).getSections().add(section);
            }
        }
        for (CourseSectionVO item : map.values()
                ) {
            resultList.add(item);
        }
        return resultList;
    }
}
