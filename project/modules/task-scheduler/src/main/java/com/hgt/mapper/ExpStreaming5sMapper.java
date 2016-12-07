package com.hgt.mapper;

import com.hgt.entity.ExpStreaming5s;

public interface ExpStreaming5sMapper {
    int deleteByPrimaryKey(String statsRid);

    int insert(ExpStreaming5s record);

    int insertSelective(ExpStreaming5s record);

    ExpStreaming5s selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(ExpStreaming5s record);

    int updateByPrimaryKey(ExpStreaming5s record);
}