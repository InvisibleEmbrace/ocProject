package com.online.college.dao;

import com.online.college.pojo.ConstsClassify;

import java.util.List;

public interface ConstsClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConstsClassify record);

    int insertSelective(ConstsClassify record);

    ConstsClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConstsClassify record);

    int updateByPrimaryKey(ConstsClassify record);

    List<ConstsClassify> queryAll();

    ConstsClassify getByCode(String code);
}