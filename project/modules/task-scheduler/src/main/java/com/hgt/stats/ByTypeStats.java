package com.hgt.stats;

import com.hgt.domain.AppsCodeCounts;
import com.hgt.domain.SimpleStringBean;
import com.hgt.domain.TypeCounts;
import com.hgt.entity.ExpStreaming5sTotal;
import com.hgt.entity.StatsByType5s;
import com.hgt.entity.StatsByType5s;
import com.hgt.mapper.ExpStreaming5sTotalMapper;
import com.hgt.mapper.StatsByType5sMapper;
import com.hgt.mapper.StatsByTypeMapper;
import com.hgt.tools.StatsIdBuilder;
import com.hgt.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * README: 统计异常日志的定时任务
 * Created by Yao on 12/7/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class ByTypeStats {

    private final String BASE_URL = "logb/stats/type";

    @Autowired
    StatsByTypeMapper statsByTypeMapper;

    @Autowired
    StatsByType5sMapper statsByType5sMapper;

    @RequestMapping(value = BASE_URL + "/counts/10s", method = RequestMethod.GET)
    public void statsTypeCountsBy5s() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> dateMap = new HashMap<String, Object>();
        Date insertTime = new Date();
        String strInsertTime = simpleDateFormat.format(insertTime);
        String startTime = "";

        dateMap.put("endTime", insertTime);
        //设置查询时间为最新5s
        startTime = DateHelper.operateDatetimeByHour(strInsertTime, -72);
        //startTime = DateHelper.operateDatetimeBySecond(strInsertTime, -5);
        dateMap.put("startTime", startTime);

        //region 统计5s内的某一类型日志总数，并入库
        List<TypeCounts> firstResult  = statsByTypeMapper.selectTypeCountsByTimePeriod(dateMap);
        List<StatsByType5s> list = new ArrayList<>();

        for (TypeCounts item : firstResult) {
            StatsByType5s newItem = new StatsByType5s();
            newItem.setStatsRid(StatsIdBuilder.build(item.getLogType(), strInsertTime));
            newItem.setStartTime(insertTime);
            newItem.setLogType(item.getLogType());
            newItem.setLogCounts(Integer.parseInt(item.getCounts()));
            list.add(newItem);
            int result4Insert = statsByType5sMapper.insert(newItem);
        }
        //endregion 统计5s内的某一类型日志总数，并入库

    }

}
