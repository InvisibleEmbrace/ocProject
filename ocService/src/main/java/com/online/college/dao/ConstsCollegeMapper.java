package com.online.college.dao;

import com.online.college.pojo.ConstsCollege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConstsCollegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConstsCollege record);

    int insertSelective(ConstsCollege record);

    ConstsCollege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConstsCollege record);

    int updateByPrimaryKey(ConstsCollege record);

    List<ConstsCollege> queryPage(@Param("name") String name);

    ConstsCollege getByCode(String code);

    void deleteLogic(Integer id);
}