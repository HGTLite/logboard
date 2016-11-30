package com.hgt.mapper;

import com.hgt.entity.LogBeats;

public interface LogBeatsMapper {


    //========================================

    int deleteByPrimaryKey(String beatId);

    int insert(LogBeats record);

    int insertSelective(LogBeats record);

    LogBeats selectByPrimaryKey(String beatId);

    int updateByPrimaryKeySelective(LogBeats record);

    int updateByPrimaryKey(LogBeats record);
}