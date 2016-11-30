package com.hgt.mapper;

import com.hgt.entity.LogBeats;
import com.hgt.entity.StatsByApp;

import java.util.List;

public interface StatsByAppMapper {

    List<StatsByApp> selectAll();

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByApp record);

    StatsByApp selectByPrimaryKey(String statsRid);

    int updateByPrimaryKey(StatsByApp record);

    //========================================

    int insertSelective(StatsByApp record);

    int updateByPrimaryKeySelective(StatsByApp record);

}