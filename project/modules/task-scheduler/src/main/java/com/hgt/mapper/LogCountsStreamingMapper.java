package com.hgt.mapper;

import com.hgt.entity.LogCountsStreaming;

import java.util.List;

public interface LogCountsStreamingMapper {

    List<LogCountsStreaming> selectAll();

//    int updateAddCount(@Param(value = "strId") String strId, @Param(value = "intCount") int intCount);

    int updateAddCount(int intCount);

//    =================================================================================

    int deleteByPrimaryKey(String statsRid);

    int insert(LogCountsStreaming record);

    int insertSelective(LogCountsStreaming record);

    LogCountsStreaming selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(LogCountsStreaming record);

    int updateByPrimaryKey(LogCountsStreaming record);
}