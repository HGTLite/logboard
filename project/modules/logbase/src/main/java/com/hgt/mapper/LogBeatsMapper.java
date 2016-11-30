package com.hgt.mapper;

import com.hgt.entity.ExpStreaming;
import com.hgt.entity.LogBeats;

import java.util.List;

public interface LogBeatsMapper {

    List<LogBeats> selectAll();

    int deleteByPrimaryKey(String beatId);

    int insert(LogBeats record);

    LogBeats selectByPrimaryKey(String beatId);

    int updateByPrimaryKey(LogBeats record);

    //========================================

    int insertSelective(LogBeats record);

    int updateByPrimaryKeySelective(LogBeats record);

}