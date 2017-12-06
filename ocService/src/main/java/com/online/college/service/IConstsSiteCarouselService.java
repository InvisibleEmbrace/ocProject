package com.online.college.service;

import com.online.college.pojo.ConstsSiteCarousel;

import java.util.List;

public interface IConstsSiteCarouselService {

    /**
     * 查询轮播图
     * @param count
     * @return
     */
    List<ConstsSiteCarousel> queryCarousel(int count);
}
