package com.hgt.mapper;

import com.hgt.entity.LogBeatsLost;

public interface LogBeatsLostMapper {
    int deleteByPrimaryKey(String beatId);

    int insert(LogBeatsLost record);

    int insertSelective(LogBeatsLost record);

    LogBeatsLost selectByPrimaryKey(String beatId);

    int updateByPrimaryKeySelective(LogBeatsLost record);

    int updateByPrimaryKey(LogBeatsLost record);
}