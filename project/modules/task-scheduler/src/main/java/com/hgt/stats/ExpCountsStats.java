package com.hgt.stats;

import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.ExpStreaming5sTotal;
import com.hgt.entity.StatsByApp5sTotal;
import com.hgt.mapper.ExpStreaming5sTotalMapper;
import com.hgt.mapper.ExpStreamingMapper;
import com.hgt.tools.StatsIdBuilder;
import com.hgt.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * README: 统计异常日志的定时任务
 * Created by Yao on 12/7/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class ExpCountsStats {

    private final String BASE_URL = "logb/stats/exp";

    @Autowired
    ExpStreamingMapper expStreamingMapper;

    @Autowired
    ExpStreaming5sTotalMapper expStreaming5sTotalMapper;

    @RequestMapping(value = BASE_URL + "/counts/5s", method = RequestMethod.GET)
    public void statsexpCountsBy5s() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> dateMap = new HashMap<String, Object>();
        String dateNow = simpleDateFormat.format(new Date());
        String startTime = "";
        dateMap.put("endTime", dateNow);
        startTime = DateHelper.operateDatetimeByHour(dateNow, -72);
        dateMap.put("startTime", startTime);
        Date insertTime = new Date();
        String strInsertTime = simpleDateFormat.format(insertTime);

        //region 统计5s内的异常日志总数，并入库
        SimpleStringBean thisTotalCount = expStreamingMapper.selectTotalCountsByTimePeriod(dateMap);
        ExpStreaming5sTotal expStreaming5sTotal = new ExpStreaming5sTotal();
        expStreaming5sTotal.setStatsRid(StatsIdBuilder.build("TOTAL", strInsertTime));
        expStreaming5sTotal.setStartTime(insertTime);
        expStreaming5sTotal.setLogCounts(Integer.parseInt(thisTotalCount.getContents()));
        int result4InsertTotal = expStreaming5sTotalMapper.insert(expStreaming5sTotal);
        //endregion 统计5s内的异常日志总数，并入库


    }

}
