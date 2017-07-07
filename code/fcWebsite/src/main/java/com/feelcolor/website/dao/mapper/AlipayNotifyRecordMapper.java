package com.feelcolor.website.dao.mapper;

import com.feelcolor.website.model.po.AlipayNotifyRecord;

public interface AlipayNotifyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlipayNotifyRecord record);

    int insertSelective(AlipayNotifyRecord record);

    AlipayNotifyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlipayNotifyRecord record);

    int updateByPrimaryKey(AlipayNotifyRecord record);
}