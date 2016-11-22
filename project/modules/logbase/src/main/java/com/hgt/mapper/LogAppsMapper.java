package com.hgt.mapper;

import com.hgt.entity.LogApps;

import java.util.List;

public interface LogAppsMapper {

    LogApps selectLogAppsById(String laId);

    List<LogApps> selectAllLogApps();

    int insertLogApps(LogApps logApps);

    int deleteLogAppByRowId(String laId);

    int deleteLogAppByAppCode(String appCode);

    int updateByPrimaryKey(LogApps record);

    int getTableCounts();

    //========================================
    int deleteByPrimaryKey(String laId);

    int insert(LogApps record);

    int insertSelective(LogApps record);

    LogApps selectByPrimaryKey(String laId);

    int updateByPrimaryKeySelective(LogApps record);

}