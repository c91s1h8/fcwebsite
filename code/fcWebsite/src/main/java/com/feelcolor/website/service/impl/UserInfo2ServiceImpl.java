package com.feelcolor.website.service.impl;

import javax.annotation.Resource;

import com.feelcolor.website.service.UserInfo2Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.feelcolor.website.dao.mapper.UserInfoMapper;
import com.feelcolor.website.model.po.UserInfo;

@Service
public class UserInfo2ServiceImpl implements UserInfo2Service {

    @Resource
    UserInfoMapper userInfoMapper;
    
    @Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    public void insert(int i)throws RuntimeException{
        insert1(i);
        insert2(i);
    }
    
    @Transactional(rollbackFor=Exception.class)
    public void insert1(int i)throws RuntimeException{
        UserInfo u =new UserInfo();
        u.setNickName(i+"");
        userInfoMapper.insert(u);
    }
    
    @Transactional(rollbackFor=Exception.class)
    public void insert2(int i) throws RuntimeException{
        if(i==3){throw new RuntimeException();}
        UserInfo u =new UserInfo();
        u.setNickName(i+"");
        userInfoMapper.insert(u);
    }
}
