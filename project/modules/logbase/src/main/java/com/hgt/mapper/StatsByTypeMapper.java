package com.hgt.mapper;

import com.hgt.entity.LogBeats;
import com.hgt.entity.StatsByType;

import java.util.List;

public interface StatsByTypeMapper {

    List<StatsByType> selectAll();

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByType record);

    StatsByType selectByPrimaryKey(String statsRid);

    int updateByPrimaryKey(StatsByType record);

    //========================================

    int insertSelective(StatsByType record);

    int updateByPrimaryKeySelective(StatsByType record);

}