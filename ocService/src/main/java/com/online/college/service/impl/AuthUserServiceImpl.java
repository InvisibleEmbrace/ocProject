package com.online.college.service.impl;

import com.github.pagehelper.PageHelper;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.dao.AuthUserMapper;
import com.online.college.pojo.AuthUser;
import com.online.college.service.IAuthUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iAuthUserService")
public class AuthUserServiceImpl implements IAuthUserService {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public AuthUser getUser(int id) {
        AuthUser authUser = authUserMapper.selectByPrimaryKey(id);
        return authUser;
    }

    @Override
    public List<AuthUser> queryRecomd(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AuthUser> authUserList = authUserMapper.queryRecomd();
        //处理头像
        if (CollectionUtils.isNotEmpty(authUserList)) {
            for (AuthUser user: authUserList
                 ) {
                if (StringUtils.isNotBlank(user.getHeader())) {
                    user.setHeader(QiniuStorage.getUrl(user.getHeader()));
                }
            }
        }
        return authUserList;
    }
}
