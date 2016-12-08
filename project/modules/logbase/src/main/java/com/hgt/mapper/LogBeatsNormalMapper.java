package com.hgt.mapper;

import com.hgt.entity.LogBeatsNormal;

import java.util.List;

public interface LogBeatsNormalMapper {

    List<LogBeatsNormal> selectAll();

    int deleteByPrimaryKey(String beatId);

    int insert(LogBeatsNormal record);

    int insertSelective(LogBeatsNormal record);

    LogBeatsNormal selectByPrimaryKey(String beatId);

    int updateByPrimaryKeySelective(LogBeatsNormal record);

    int updateByPrimaryKey(LogBeatsNormal record);
}