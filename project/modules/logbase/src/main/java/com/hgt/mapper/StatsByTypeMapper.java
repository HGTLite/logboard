package com.hgt.mapper;

import com.hgt.entity.StatsByType;

public interface StatsByTypeMapper {




    //========================================
    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByType record);

    int insertSelective(StatsByType record);

    StatsByType selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(StatsByType record);

    int updateByPrimaryKey(StatsByType record);
}