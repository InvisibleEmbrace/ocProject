package com.online.college.service.impl;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.dao.ConstsSiteCarouselMapper;
import com.online.college.pojo.ConstsSiteCarousel;
import com.online.college.service.IConstsSiteCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iConstsSiteCarouselService")
public class ConstsSiteCarouselImpl implements IConstsSiteCarouselService {

    @Autowired
    private ConstsSiteCarouselMapper constsSiteCarouselMapper;

    /**
     * 查询轮播图
     * @param count
     * @return
     */
    @Override
    public List<ConstsSiteCarousel> queryCarousel(int count) {
        List<ConstsSiteCarousel> carouselList = constsSiteCarouselMapper.queryCarousel(count);
        //处理为七牛云图片连接
        for (ConstsSiteCarousel carouse: carouselList
             ) {
            carouse.setPicture(QiniuStorage.getUrl(carouse.getPicture()));
        }
        return carouselList;
    }
}
