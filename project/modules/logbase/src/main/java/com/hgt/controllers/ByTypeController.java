package com.hgt.controllers;

import com.hgt.domain.AppsCodeCounts;
import com.hgt.domain.DataResult;
import com.hgt.mapper.StatsByAppMapper;
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
 * README: 关于按类型查询的接口
 * Created by Yao on 12/6/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class ByTypeController {

    private final String BASE_URL = "logb/by/type";

    @Autowired
    StatsByAppMapper statsByAppMapper;

    /**
     * 输入时间的前一天，按日志类型统计的日志数
     *
     * @param strDate
     * @return
     */
    @RequestMapping(value = BASE_URL + "/bubbles/yesterday/{strDate}", method = RequestMethod.GET)
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
            startTime = DateHelper.operateDatetimeByHour(dateNow, -1);
        } else {
            dateMap.put("endTime", strDate);
            startTime = DateHelper.operateDatetimeByHour(strDate, -1);
        }
        dateMap.put("startTime", startTime);

        List<AppsCodeCounts> queryResults = statsByAppMapper.selectAllByTimePeriod(dateMap);

        //region 只显示前8个应用的日志百分比，其他应用作为一个整体显示
        int resultSize = queryResults.size();

        //设置显示的应用数
        int primaryApp = 8;

        if (resultSize > primaryApp) {

            for (int n = 0; n < primaryApp; n++) {
                codeCountsList.add(queryResults.get(n));
            }

            AppsCodeCounts other = new AppsCodeCounts();
            other.setAppCode("其他系统");
            int otherCounts = 0;
            for (int i = primaryApp; i < resultSize; i++) {
                otherCounts += Integer.parseInt(queryResults.get(i).getCounts());
            }
            other.setCounts(otherCounts + "");

            codeCountsList.add(other);
        } else {
            codeCountsList = queryResults;
        }
        //endregion

        return new DataResult<>(codeCountsList);

    }


}
