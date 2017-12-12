package com.online.college.service;

import com.github.pagehelper.PageInfo;
import com.online.college.pojo.ConstsSiteCarousel;

import java.util.List;

public interface IConstsSiteCarouselService {

    /**
     * 查询轮播图
     * @param count
     * @return
     */
    List<ConstsSiteCarousel> queryCarousel(int count);


    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo queryPage(Integer pageNum, Integer pageSize);

    ConstsSiteCarousel getById(Integer id);

    void insert(ConstsSiteCarousel constsSiteCarousel);

    void update(ConstsSiteCarousel constsSiteCarousel);

    void delete(Integer id);

}
