package com.hgt.mapper;

import com.hgt.entity.ExpStreaming;

public interface ExpStreamingMapper {


    //========================================
    int deleteByPrimaryKey(String statsRid);

    int insert(ExpStreaming record);

    int insertSelective(ExpStreaming record);

    ExpStreaming selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(ExpStreaming record);

    int updateByPrimaryKey(ExpStreaming record);
}