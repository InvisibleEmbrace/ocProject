package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.pojo.AuthUser;
import com.online.college.pojo.CourseComment;
import com.online.college.pojo.CourseSection;
import com.online.college.service.IAuthUserService;
import com.online.college.service.ICourseCommentService;
import com.online.college.service.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/courseComment")
public class CourseCommentController {

    @Autowired
    private ICourseCommentService iCourseCommentService;

    @Autowired
    private IAuthUserService iAuthUserService;

    @Autowired
    private ICourseSectionService iCourseSectionService;

    /**
     * 加载评论&答疑
     * sectionId：章节id
     * courseId ：课程id
     * type : 类型
     *
     * @return
     */
    @RequestMapping("/segment")
    public ModelAndView segment(CourseComment courseComment, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        if (courseComment.getCourseId() == null && courseComment.getType() == null) {
            return new ModelAndView("error/404");
        }
        ModelAndView mv = new ModelAndView("commentSegment");
        PageInfo commentPage = iCourseCommentService.queryList(courseComment.getCourseId(), courseComment.getType(), pageNum, pageSize);
        mv.addObject("page",commentPage );
        return mv;
    }

    /**
     * 发表评论
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/doComment")
    public String doComment(HttpServletRequest request, CourseComment entity, String indeityCode) {

        //验证码判断
        if (null == indeityCode ||
                (indeityCode != null && !indeityCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request)))) {
            return new JsonView(2).toString();//验证码错误
        }

        //文字太长
        if (entity.getContent().trim().length() > 200 || entity.getContent().trim().length() == 0) {
            return new JsonView(3).toString();//文字太长或者为空
        }

        if (entity.getRefId() != null) {//来自于个人中心评论
            CourseComment refComment = iCourseCommentService.getById(entity.getRefId());
            if (refComment != null) {
                CourseSection courseSection = iCourseSectionService.getById(refComment.getSectionId());

                entity.setRefContent(refComment.getContent());
                entity.setRefId(entity.getRefId());
                entity.setCourseId(refComment.getCourseId());
                entity.setSectionId(refComment.getSectionId());
                entity.setSectionTitle(courseSection.getName());
                entity.setToUsername(refComment.getUsername());//引用的评论的username
                entity.setUsername(SessionContext.getUsername());
                entity.setCreateUser(SessionContext.getUsername());
                entity.setUpdateUser(SessionContext.getUsername());

                iCourseCommentService.insertCourseComment(entity);
                return new JsonView(0).toString();
            }

        } else {
            CourseSection courseSection = iCourseSectionService.getById(entity.getSectionId());
            if (courseSection != null) {
                Integer userId = SessionContext.getUserId();
                AuthUser user = iAuthUserService.getById(userId);
                entity.setSectionTitle(courseSection.getName());
                entity.setToUsername(entity.getCreateUser());//toUsername可以作为页面入参
                entity.setUsername(user.getUsername());
                entity.setCreateUser(user.getUsername());
                entity.setUpdateUser(user.getUsername());

                iCourseCommentService.insertCourseComment(entity);
                return new JsonView(0).toString();
            }
        }
        return new JsonView(1).toString();
    }
}
