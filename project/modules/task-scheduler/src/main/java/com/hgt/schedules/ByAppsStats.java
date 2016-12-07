package com.hgt.schedules;

import com.hgt.domain.AppsCodeCounts;
import com.hgt.entity.StatsByApp;
import com.hgt.entity.StatsByApp5s;
import com.hgt.mapper.StatsByApp5sMapper;
import com.hgt.mapper.StatsByAppMapper;
import com.hgt.tools.StatsIdBuilder;
import com.hgt.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * README: 按应用统计日志的定时任务
 * Created by Yao on 12/7/16.
 * =============================================================================
 * CHANGELOG:
 */

public class ByAppsStats {


    @Autowired
    StatsByAppMapper statsByAppMapper;

    @Autowired
    StatsByApp5sMapper statsByApp5sMapper;

    public void statsLogCountsByAppBy5s() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> dateMap = new HashMap<String, Object>();
        String dateNow = simpleDateFormat.format(new Date());
        String startTime = "";
        dateMap.put("endTime", dateNow);
        startTime = DateHelper.operateDatetimeByHour(dateNow, -48);
        dateMap.put("startTime", startTime);
        List<AppsCodeCounts> firstResult = statsByAppMapper.selectAllByTimePeriod(dateMap);

        List<StatsByApp5s> list = new ArrayList<>();
        Date insertTime = new Date();
        String strInsertTime = simpleDateFormat.format(insertTime);

        for (AppsCodeCounts item : firstResult) {
            StatsByApp5s newItem = new StatsByApp5s();
            newItem.setStatsRid(StatsIdBuilder.build(item.getAppCode(), strInsertTime));
            newItem.setStartTime(insertTime);
            newItem.setAppCode(item.getAppCode());
            newItem.setLogCounts(Integer.parseInt(item.getCounts()));
            list.add(newItem);
            int result4Insert = statsByApp5sMapper.insert(newItem);
        }


    }

}
