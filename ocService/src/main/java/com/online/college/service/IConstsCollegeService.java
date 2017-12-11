package com.online.college.service;

import com.github.pagehelper.PageInfo;
import com.online.college.pojo.ConstsCollege;

public interface IConstsCollegeService {

    PageInfo queryPage(String name, Integer pageNum, Integer pageSize);

    ConstsCollege getById(Integer id);

    ConstsCollege getByCode(String code);

    void insert(ConstsCollege constsCollege);

    void deleteLogic(Integer id);

    void update(ConstsCollege constsCollege);

    void delete(Integer id);
}
