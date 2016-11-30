package com.hgt.mapper;

import com.hgt.entity.StatsByApp;

public interface StatsByAppMapper {





    //========================================

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByApp record);

    int insertSelective(StatsByApp record);

    StatsByApp selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(StatsByApp record);

    int updateByPrimaryKey(StatsByApp record);
}