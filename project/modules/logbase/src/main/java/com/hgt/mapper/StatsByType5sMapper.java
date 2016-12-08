package com.hgt.mapper;

import com.hgt.entity.StatsByType5s;

public interface StatsByType5sMapper {
    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByType5s record);

    int insertSelective(StatsByType5s record);

    StatsByType5s selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(StatsByType5s record);

    int updateByPrimaryKey(StatsByType5s record);
}