package com.online.college.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.online.college.dao.ConstsCollegeMapper;
import com.online.college.pojo.ConstsCollege;
import com.online.college.service.IConstsCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iConstsCollegeService")
public class ConstsCollegeServiceImpl implements IConstsCollegeService {

    @Autowired
    private ConstsCollegeMapper constsCollegeMapper;

    @Override
    public PageInfo queryPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConstsCollege> list = constsCollegeMapper.queryPage(name);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public ConstsCollege getById(Integer id) {
        return constsCollegeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ConstsCollege getByCode(String code) {
        return constsCollegeMapper.getByCode(code);
    }

    @Override
    public void insert(ConstsCollege constsCollege) {
        constsCollegeMapper.insertSelective(constsCollege);
    }

    @Override
    public void deleteLogic(Integer id) {

    }

    @Override
    public void update(ConstsCollege constsCollege) {
        constsCollegeMapper.updateByPrimaryKeySelective(constsCollege);
    }

    @Override
    public void delete(Integer id) {
        constsCollegeMapper.deleteByPrimaryKey(id);
    }

}
