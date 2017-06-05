package com.feelcolor.website.dao.mapper;

import com.feelcolor.website.model.po.DeviceAgentRel;

public interface DeviceAgentRelMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(DeviceAgentRel record);

    int insertSelective(DeviceAgentRel record);

    DeviceAgentRel selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(DeviceAgentRel record);

    int updateByPrimaryKey(DeviceAgentRel record);
}