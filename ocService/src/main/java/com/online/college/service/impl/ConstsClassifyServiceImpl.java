package com.online.college.service.impl;

import com.online.college.dao.ConstsClassifyMapper;
import com.online.college.pojo.ConstsClassify;
import com.online.college.service.IConstsClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iConstsClassifyService")
public class ConstsClassifyServiceImpl implements IConstsClassifyService {

    @Autowired
    private ConstsClassifyMapper constsClassifyMapper;

    @Override
    public ConstsClassify getById(int id) {
        ConstsClassify constsClassify = constsClassifyMapper.selectByPrimaryKey(id);
        return constsClassify;
    }

    @Override
    public List<ConstsClassify> queryAll() {
        List<ConstsClassify> constsClassifyList = constsClassifyMapper.queryAll();
        return constsClassifyList;
    }

    @Override
    public ConstsClassify getByCode(String code) {
        ConstsClassify classify = constsClassifyMapper.getByCode(code);
        return classify;
    }

    @Override
    public void insert(ConstsClassify constsClassify) {
        constsClassifyMapper.insertSelective(constsClassify);
    }

    @Override
    public void delete(Integer id) {
        constsClassifyMapper.deleteByPrimaryKey(id);
    }

}
