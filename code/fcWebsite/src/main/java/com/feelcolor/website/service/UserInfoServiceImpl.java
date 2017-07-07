package com.feelcolor.website.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.feelcolor.website.dao.mapper.UserInfoMapper;
import com.feelcolor.website.model.po.UserInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;
    
    @Resource
    UserInfo2Service userInfo2Service;
    
    @Override
    public UserInfo selectByPrimaryKey(String id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

  
    public void test() throws RuntimeException {
        for (int i = 0; i < 10; i++) {
        userInfo2Service.insert(i);
        }
    }
    
    


}
