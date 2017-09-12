package com.feelcolor.website.service.impl;

import com.feelcolor.website.dao.mapper.UserInfoMapper;
import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserById(String id) {
        return null;
    }

    @Override
    public int inserUser(UserInfo user) {
        return userInfoMapper.insertSelective(user);
    }

    @Override
    public PageImpl<UserInfo> getUserList(Integer id, String name, Integer age, Pageable pageable) {
        return null;
    }
}
