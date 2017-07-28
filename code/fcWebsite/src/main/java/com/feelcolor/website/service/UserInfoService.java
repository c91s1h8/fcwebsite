package com.feelcolor.website.service;

import com.feelcolor.website.model.po.UserInfo;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface UserInfoService {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    UserInfo selectByPrimaryKey(String id);

    /**
     * @throws RuntimeException
     */
    void test() throws RuntimeException;

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int inserUser(UserInfo user);

    /**
     * 
     * @param id
     * @param name
     * @param age
     * @param pageable
     * @return
     */
    PageImpl<UserInfo> getUserList(Integer id, String name, Integer age, Pageable pageable);
}
