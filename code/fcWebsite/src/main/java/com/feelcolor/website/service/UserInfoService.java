package com.feelcolor.website.service;

import com.feelcolor.website.model.po.UserInfo;

public interface UserInfoService {
    
    UserInfo selectByPrimaryKey(String id);
}
