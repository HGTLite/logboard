package com.hgt.mapper;

import com.hgt.entity.ExpStreaming;

import java.util.List;

public interface ExpStreamingMapper {

    List<ExpStreaming> selectAll();

    ExpStreaming selectByPrimaryKey(String statsRid);

    int deleteByPrimaryKey(String statsRid);

    int insert(ExpStreaming record);

    int updateByPrimaryKey(ExpStreaming record);

    //========================================

    int insertSelective(ExpStreaming record);

    int updateByPrimaryKeySelective(ExpStreaming record);

}