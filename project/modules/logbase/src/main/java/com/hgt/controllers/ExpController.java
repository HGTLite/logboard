//package com.hgt.controllers;
//
//import com.hgt.domain.AppsCodeCounts;
//import com.hgt.domain.DataResult;
//import com.hgt.domain.TimeCounts;
//import com.hgt.utils.DateHelper;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * README: 关于异常统计的接口
// * Created by Yao on 12/6/16.
// * =============================================================================
// * CHANGELOG:
// */
//public class ExpController {
//
//    private final String BASE_URL = "logb/exp";
//
//    @RequestMapping(value = BASE_URL + "/5min/{strDate}", method = RequestMethod.GET)
//    public DataResult<List<TimeCounts>> queryExpCountsCurve(@PathVariable String strDate) {
//        List<TimeCounts> codeCountsList = new ArrayList<>();
//        String dateInput = strDate;
//        // System.out.println(dateInput);
//        // TODO: 12/6/16 日期格式异常处理
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        HashMap<String, Object> dateMap = new HashMap<String, Object>();
//        String dateNow = simpleDateFormat.format(new Date());
//        String startTime = "";
////        if (dateInput == null || dateInput.trim().length() == 0) {
////            dateMap.put("endTime", dateNow);
////            startTime = DateHelper.operateDatetimeByHour(dateNow, -1);
////        } else {
////            dateMap.put("endTime", strDate);
////            startTime = DateHelper.operateDatetimeByHour(strDate, -1);
////        }
//        dateMap.put("startTime", startTime);
//
////        List<AppsCodeCounts> queryResults = statsByAppMapper.selectAllByTimePeriod(dateMap);
//
//        return new DataResult<>(codeCountsList);
//
//    }
//}
