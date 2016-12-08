package com.hgt.controllers;

import com.hgt.domain.DataResult;
import com.hgt.domain.TimeCounts;
import com.hgt.mapper.ExpStreaming5sTotalMapper;
import com.hgt.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * README: 关于异常统计的接口
 * Created by Yao on 12/6/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class ExpController {

    private final String BASE_URL = "logb/exp";

    @Autowired
    ExpStreaming5sTotalMapper expStreaming5sTotalMapper;

    /**
     * 输入时间起点查询5分钟内，按5s统计的异常总数
     *
     * @param strDate
     * @return
     */
    @RequestMapping(value = BASE_URL + "/curve/exp/5min/{strDate}", method = RequestMethod.GET)
    public DataResult<List<TimeCounts>> queryExpLogTotal(@PathVariable String strDate) {

        List<TimeCounts> countList = new ArrayList<>();
        String dateInput = strDate;
        // System.out.println(dateInput);
        // TODO: 12/6/16 日期格式异常处理

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> dateMap = new HashMap<String, Object>();
        String dateNow = simpleDateFormat.format(new Date());
        String startTime = "";

        if (dateInput == null || dateInput.trim().length() == 0) {
            dateMap.put("endTime", dateNow);
            //设置查询的时间起点5分钟
            startTime = DateHelper.operateDatetimeByHour(dateNow, -72);
//            startTime = DateHelper.operateDatetimeBySecond(dateNow, -300);
        } else {
            dateMap.put("endTime", strDate);
            startTime = DateHelper.operateDatetimeBySecond(strDate, -300);
        }
        dateMap.put("startTime", startTime);

        List<TimeCounts> queryResults = expStreaming5sTotalMapper.selectAllByTimePeriod(dateMap);

//        System.out.println("查询结果条数是："+queryResults.size()+", 检查是否是60条");

        countList = queryResults;

        return new DataResult<>(countList);

    }

}
