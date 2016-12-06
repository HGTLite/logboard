package com.hgt.controllers;

import com.hgt.domain.DataResult;
import com.hgt.domain.AppsCodeCounts;
import com.hgt.entity.StatsByApp;
import com.hgt.mapper.StatsByAppMapper;
import com.hgt.service.StatsByAppService;
import com.hgt.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * README: 关于按应用查询的接口
 * Created by Yao on 12/6/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class ByAppsController {

    private final String BASE_URL = "logb/by/apps";

    @Autowired
    StatsByAppMapper statsByAppMapper;

    @RequestMapping(value = BASE_URL + "/pie/hour/{strDate}", method = RequestMethod.GET)
    public DataResult<List<AppsCodeCounts>> queryGeneralLogPieData(@PathVariable String strDate) {
        List<AppsCodeCounts> codeCountsList = new ArrayList<>();
        String dateInput = strDate;
        // System.out.println(dateInput);
        // TODO: 12/6/16 日期格式异常处理

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> dateMap = new HashMap<String, Object>();
        String dateNow = simpleDateFormat.format(new Date());
        String startTime = "";
        if (dateInput == null || dateInput.trim().length() == 0) {
            dateMap.put("endTime", dateNow);
            startTime = DateHelper.operateDateByHour(dateNow, -1);
        } else {
            dateMap.put("endTime", strDate);
            startTime = DateHelper.operateDateByHour(strDate, -1);
        }
        dateMap.put("startTime", startTime);

        List<AppsCodeCounts> queryResults = statsByAppMapper.selectAllByTimePeriod(dateMap);

        return new DataResult<>(queryResults);

    }

}
