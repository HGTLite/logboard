package com.hgt.mapper;

import com.hgt.domain.AppsCodeCounts;
import com.hgt.domain.SimpleStringBean;
import com.hgt.domain.TimeCounts;
import com.hgt.entity.StatsByApp5sTotal;

import java.util.HashMap;
import java.util.List;

public interface StatsByApp5sTotalMapper {

    List<TimeCounts> selectAllByTimePeriod(HashMap<String, Object> map);

    int deleteByPrimaryKey(String statsRid);

    int insert(StatsByApp5sTotal record);

    int insertSelective(StatsByApp5sTotal record);

    StatsByApp5sTotal selectByPrimaryKey(String statsRid);

    int updateByPrimaryKeySelective(StatsByApp5sTotal record);

    int updateByPrimaryKey(StatsByApp5sTotal record);
}