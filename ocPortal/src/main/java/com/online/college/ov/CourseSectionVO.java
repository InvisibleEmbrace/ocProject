package com.online.college.ov;

import com.online.college.pojo.CourseSection;

import java.util.ArrayList;
import java.util.List;

public class CourseSectionVO extends CourseSection {

    //小节
    private List<CourseSection> sections = new ArrayList<>();

    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }
}
