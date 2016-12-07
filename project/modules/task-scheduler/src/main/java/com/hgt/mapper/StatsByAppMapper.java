package com.hgt.mapper;

import com.hgt.domain.AppsCodeCounts;
import com.hgt.entity.StatsByApp;

import java.util.HashMap;
import java.util.List;

public interface StatsByAppMapper {


    List<AppsCodeCounts> selectAllByTimePeriod(HashMap<String, Object> map);

    List<StatsByApp> selectAll();

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByApp record);

    StatsByApp selectByPrimaryKey(String statsRid);

    int updateByPrimaryKey(StatsByApp record);

    //========================================

    int insertSelective(StatsByApp record);

    int updateByPrimaryKeySelective(StatsByApp record);

}