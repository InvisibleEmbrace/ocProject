package com.online.college.dao;

import com.online.college.pojo.ConstsSiteCarousel;

import java.util.List;

public interface ConstsSiteCarouselMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConstsSiteCarousel record);

    int insertSelective(ConstsSiteCarousel record);

    ConstsSiteCarousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConstsSiteCarousel record);

    int updateByPrimaryKey(ConstsSiteCarousel record);

    List<ConstsSiteCarousel> queryCarousel(int count);

    List<ConstsSiteCarousel> queryPage();

}