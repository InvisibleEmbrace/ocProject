package com.online.college.service;

import com.online.college.pojo.ConstsClassify;

import java.util.List;

public interface IConstsClassifyService {

    /**
     *根据id获取
     **/
    ConstsClassify getById(int id);


    /**
     *获取所有
     **/
    List<ConstsClassify> queryAll();

    /***
     * 根据code获取分类
     * @param code
     * @return
     */
    ConstsClassify getByCode(String code);

    void insert(ConstsClassify constsClassify);

    void delete(Integer id);
}
