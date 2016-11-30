package com.hgt.mapper;

import com.hgt.entity.ExpStreaming;
import com.hgt.entity.LogApps;

import java.util.List;

public interface ExpStreamingMapper {

    List<ExpStreaming> selectAll();

    ExpStreaming selectByPrimaryKey(String statsRid);

    int insert(ExpStreaming record);

    int deleteByPrimaryKey(String statsRid);

    int updateByPrimaryKey(ExpStreaming record);

    //========================================

    int insertSelective(ExpStreaming record);

    int updateByPrimaryKeySelective(ExpStreaming record);

}