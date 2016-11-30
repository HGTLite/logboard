package com.hgt.mapper;

import com.hgt.entity.StatsByLevel;

public interface StatsByLevelMapper {



    //========================================

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByLevel record);

    int insertSelective(StatsByLevel record);

    StatsByLevel selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(StatsByLevel record);

    int updateByPrimaryKey(StatsByLevel record);
}