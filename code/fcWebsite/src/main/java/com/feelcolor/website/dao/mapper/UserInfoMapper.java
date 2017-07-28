package com.feelcolor.website.dao.mapper;

import com.feelcolor.website.model.po.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    Integer getTotalRecord(@Param("id") Integer id, @Param("name") String name, @Param("age") Integer age);

    List<UserInfo> selectWithPage(@Param("id") Integer id, @Param("name") String name, @Param("age") Integer age,
            @Param("pageStart") int offset, @Param("pageSize") int pageSize);
}