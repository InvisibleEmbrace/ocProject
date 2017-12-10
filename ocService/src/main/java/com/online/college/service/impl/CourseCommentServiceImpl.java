package com.online.college.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.dao.CourseCommentMapper;
import com.online.college.pojo.AuthUser;
import com.online.college.pojo.CourseComment;
import com.online.college.pojo.CourseCommentDto;
import com.online.college.pojo.CourseSection;
import com.online.college.service.IAuthUserService;
import com.online.college.service.ICourseCommentService;
import com.online.college.service.ICourseSectionService;
import com.online.college.vo.CourseCommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程评论&答疑
 */
@Service("iCourseCommentService")
public class CourseCommentServiceImpl implements ICourseCommentService {

    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Autowired
    private IAuthUserService iAuthUserService;

    @Autowired
    private ICourseSectionService iCourseSectionService;


    @Override
    public PageInfo queryList(Integer courseId, Integer type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseCommentVO> commentVOList = new ArrayList<>();
        List<CourseComment> courseCommentList = courseCommentMapper.queryList(courseId, type);
        PageInfo pageInfo = new PageInfo(courseCommentList);
        for (CourseComment item : courseCommentList
                ) {
            CourseCommentVO vo = new CourseCommentVO();
            BeanUtils.copyProperties(item,vo);
            AuthUser user = iAuthUserService.getByUsername(item.getUsername());
            vo.setHeader(QiniuStorage.getUrl(user.getHeader()));
            CourseSection section = iCourseSectionService.getById(courseId);
            vo.setCourseName(section.getName());
            commentVOList.add(vo);
        }
        pageInfo.setList(commentVOList);
        return pageInfo;
    }

    @Override
    public CourseComment getById(Integer id) {
        return courseCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertCourseComment(CourseComment courseComment) {
        courseCommentMapper.insertSelective(courseComment);
    }

    @Override
    public PageInfo querCommentByUsername(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseCommentDto> list = courseCommentMapper.queryCommentByUsername(username);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
