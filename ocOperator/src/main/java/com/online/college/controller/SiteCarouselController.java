package com.online.college.controller;

import com.github.pagehelper.PageInfo;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.web.JsonView;
import com.online.college.pojo.ConstsSiteCarousel;
import com.online.college.service.IConstsSiteCarouselService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 轮播配置
 */
@RestController
@RequestMapping("/carousel")
public class SiteCarouselController {

    @Autowired
    private IConstsSiteCarouselService iConstsSiteCarouselService;

    @RequestMapping(value = "/queryPage")
    public ModelAndView queryPage(ConstsSiteCarousel queryEntity, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        ModelAndView mv = new ModelAndView("cms/carousel/pagelist");
        mv.addObject("curNav", "carousel");
        PageInfo page = iConstsSiteCarouselService.queryPage(pageNum, pageSize);
        mv.addObject("page", page);
        mv.addObject("queryEntity", queryEntity);
        return mv;
    }


    @RequestMapping(value = "/toMerge")
    public ModelAndView toMerge(ConstsSiteCarousel entity){
        ModelAndView mv = new ModelAndView("cms/carousel/merge");
        mv.addObject("curNav", "carousel");

        if(entity.getId() != null){
            entity = iConstsSiteCarouselService.getById(entity.getId());
            if(null != entity && StringUtils.isNotEmpty(entity.getPicture())){
                String pictureUrl = QiniuStorage.getUrl(entity.getPicture(), ThumbModel.THUMB_128);
                entity.setPicture(pictureUrl);
            }
        }
        mv.addObject("entity",entity);
        return mv;
    }


    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(ConstsSiteCarousel entity,@RequestParam MultipartFile pictureImg){
        String key = null;
        try {
            if (null != pictureImg && pictureImg.getBytes().length > 0) {
                key = QiniuStorage.uploadImage(pictureImg.getBytes());
                entity.setPicture(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(entity.getId() == null){
            iConstsSiteCarouselService.insert(entity);
        }else{
            iConstsSiteCarouselService.update(entity);
        }
        return new ModelAndView("redirect:/carousel/queryPage.html");
    }


    @RequestMapping(value = "/delete")
    public String delete(ConstsSiteCarousel entity){
        iConstsSiteCarouselService.delete(entity.getId());
        return new JsonView().toString();
    }
}
