package com.hgt.mapper;

import com.hgt.entity.LogApps;

import java.util.List;

public interface LogAppsMapper {

    LogApps selectLogAppsById(String laId);

    List<LogApps> selectAllLogApps();

    int insertLogApps(LogApps logApps);

    int deleteLogAppsById(String laId);


    int insert(LogApps record);

    int insertSelective(LogApps record);
}