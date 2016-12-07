package com.hgt.mapper;

import com.hgt.entity.StatsByLevel;

import java.util.List;

public interface StatsByLevelMapper {

    List<StatsByLevel> selectAll();

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByLevel record);

    StatsByLevel selectByPrimaryKey(String statsRid);

    int updateByPrimaryKey(StatsByLevel record);

    //========================================

    int insertSelective(StatsByLevel record);

    int updateByPrimaryKeySelective(StatsByLevel record);

}