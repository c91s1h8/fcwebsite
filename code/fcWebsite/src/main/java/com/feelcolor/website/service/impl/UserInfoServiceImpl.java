package com.feelcolor.website.service.impl;

import com.feelcolor.website.dao.mapper.UserInfoMapper;
import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfo2Service;
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
    @Value("${app.socket.port}")
    private Integer port;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserInfo2Service userInfo2Service;

    @Override

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

    @Override
    @Transactional(readOnly = true)
    public PageImpl<UserInfo> getUserList(Integer id, String name, Integer age,
            @PageableDefault(value = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
        Integer total = userInfoMapper.getTotalRecord(id, name, age);
        List<UserInfo> list = userInfoMapper.selectWithPage(id, name, age, pageable.getOffset(),
                pageable.getPageSize());
        return new PageImpl<UserInfo>(list, pageable, total);
    }

}
