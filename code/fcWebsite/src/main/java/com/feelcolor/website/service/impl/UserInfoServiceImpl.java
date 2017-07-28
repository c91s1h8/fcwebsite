package com.feelcolor.website.service.impl;

import com.feelcolor.website.dao.mapper.UserInfoMapper;
import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfo2Service;
import com.feelcolor.website.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserInfo2Service userInfo2Service;

    @Override
    @Transactional(readOnly = true)
    public UserInfo selectByPrimaryKey(String id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public void test() throws RuntimeException {
        for (int i = 0; i < 10; i++) {
            userInfo2Service.insert(i);
        }
    }

    @Override
    public int inserUser(UserInfo user) {
        return userInfoMapper.insert(user);
    }

}
