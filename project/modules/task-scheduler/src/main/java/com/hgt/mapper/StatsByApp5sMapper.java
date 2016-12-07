package com.hgt.mapper;

import com.hgt.entity.StatsByApp5s;

public interface StatsByApp5sMapper {
    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByApp5s record);

    int insertSelective(StatsByApp5s record);

    StatsByApp5s selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(StatsByApp5s record);

    int updateByPrimaryKey(StatsByApp5s record);
}