package com.hgt.mapper;

import com.hgt.domain.AppsCodeCounts;
import com.hgt.domain.TypeCounts;
import com.hgt.entity.StatsByType;

import java.util.HashMap;
import java.util.List;

public interface StatsByTypeMapper {


    //与logbase相比，对查询结果顺序有改动
    List<TypeCounts> selectTypeCountsByTimePeriod(HashMap<String, Object> map);

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByType record);

    StatsByType selectByPrimaryKey(String statsRid);

    int updateByPrimaryKey(StatsByType record);

    //========================================

    int insertSelective(StatsByType record);

    int updateByPrimaryKeySelective(StatsByType record);

}