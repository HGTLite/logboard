package com.hgt.mapper;

import com.hgt.entity.ExpStreaming5sTotal;

public interface ExpStreaming5sTotalMapper {



    int deleteByPrimaryKey(String statsRid);

    int insert(ExpStreaming5sTotal record);

    int insertSelective(ExpStreaming5sTotal record);

    ExpStreaming5sTotal selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(ExpStreaming5sTotal record);

    int updateByPrimaryKey(ExpStreaming5sTotal record);
}