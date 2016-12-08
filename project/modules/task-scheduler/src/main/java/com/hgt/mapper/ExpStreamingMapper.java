package com.hgt.mapper;

import com.hgt.domain.AppsCodeCounts;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.ExpStreaming;

import java.util.HashMap;
import java.util.List;

public interface ExpStreamingMapper {

    List<AppsCodeCounts> selectExpCountsByTimePeriod(HashMap<String, Object> map);

    SimpleStringBean selectTotalCountsByTimePeriod(HashMap<String, Object> map);

    List<ExpStreaming> selectAll();

    ExpStreaming selectByPrimaryKey(String statsRid);

    int deleteByPrimaryKey(String statsRid);

    int insert(ExpStreaming record);

    int updateByPrimaryKey(ExpStreaming record);

    //========================================

    int insertSelective(ExpStreaming record);

    int updateByPrimaryKeySelective(ExpStreaming record);



}