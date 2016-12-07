package com.hgt.mapper;

import com.hgt.entity.StatsByApp5sTotal;

public interface StatsByApp5sTotalMapper {

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByApp5sTotal record);

    int insertSelective(StatsByApp5sTotal record);

    StatsByApp5sTotal selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(StatsByApp5sTotal record);

    int updateByPrimaryKey(StatsByApp5sTotal record);
}