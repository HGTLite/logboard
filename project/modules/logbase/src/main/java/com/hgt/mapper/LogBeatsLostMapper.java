package com.hgt.mapper;

import com.hgt.entity.LogBeatsLost;

import java.util.List;

public interface LogBeatsLostMapper {

    List<LogBeatsLost> selectAll();

    int deleteByPrimaryKey(String beatId);

    int insert(LogBeatsLost record);

    int insertSelective(LogBeatsLost record);

    LogBeatsLost selectByPrimaryKey(String beatId);

    int updateByPrimaryKeySelective(LogBeatsLost record);

    int updateByPrimaryKey(LogBeatsLost record);
}