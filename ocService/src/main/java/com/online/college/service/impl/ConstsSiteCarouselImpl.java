package com.online.college.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo queryPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConstsSiteCarousel> list = constsSiteCarouselMapper.queryPage();
        for (ConstsSiteCarousel item: list
             ) {
            item.setPicture(QiniuStorage.getUrl(item.getPicture()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public ConstsSiteCarousel getById(Integer id) {
        return constsSiteCarouselMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(ConstsSiteCarousel constsSiteCarousel) {
        constsSiteCarouselMapper.insertSelective(constsSiteCarousel);
    }

    @Override
    public void update(ConstsSiteCarousel constsSiteCarousel) {
        constsSiteCarouselMapper.updateByPrimaryKeySelective(constsSiteCarousel);
    }

    @Override
    public void delete(Integer id) {
        constsSiteCarouselMapper.deleteByPrimaryKey(id);
    }
}
