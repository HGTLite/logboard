package com.hgt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogCountsStreaming;
import com.hgt.mapper.LogCountsStreamingMapper;
import com.hgt.service.LogCountsStreamingService;
import com.hgt.utils.DateHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * README:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
@Api(value = "统计日志处理量 val", description = "关于统计日志处理量操作的接口")
@RestController
public class LogCountsStreamingController {

    private final String BASE_URL = "logb/counts";

    @Autowired
    LogCountsStreamingService logCountsStreamingService;

    @Autowired
    LogCountsStreamingMapper logCountsStreamingMapper;

    @ApiOperation(value = "根据行id获取单条信息", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/rid/{strId}", method = RequestMethod.GET)
    public DataResult<LogCountsStreaming> findById(@PathVariable String strId) {
        return logCountsStreamingService.findById(strId);
    }

    @ApiOperation(value = "获取所有记录（不分页）", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/all", method = RequestMethod.GET)
    public DataResult<List<LogCountsStreaming>> findAll() {
        return logCountsStreamingService.findAll();
    }

    @ApiOperation(value = "获取所有记录（分页）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第pageNum页", required = true,
                    dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", required = true,
                    dataType = "int", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/all/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public DataResult<PageInfo> findAllByPages(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<LogCountsStreaming> list = logCountsStreamingMapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return new DataResult<>(pageInfo);
    }

    @ApiOperation(value = "计数加N", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/update/counts/{strId}/{intCount}", method = RequestMethod.GET)
    public DataResult<SimpleStringBean> updateAddCount(@PathVariable String strId, @PathVariable int intCount) {

        LogCountsStreaming logCountsStreaming = logCountsStreamingService.findById(strId).getData();
        logCountsStreaming.setStartTime(new Date(System.currentTimeMillis()));
        logCountsStreaming.setCounts(logCountsStreaming.getCounts() + intCount);
        logCountsStreamingMapper.updateByPrimaryKey(logCountsStreaming);
        return new DataResult<>(new SimpleStringBean("日志处理量加 " + intCount + " 成功"));
    }

//    @ApiOperation(value = "计数加N", notes = "已完成")
//    @RequestMapping(value = BASE_URL + "/update/counts/{intCount}", method = RequestMethod.GET)
//    public DataResult<SimpleStringBean> updateAddCount(@PathVariable int intCount) {
//        return logCountsStreamingService.updateAddCount(intCount);
//    }

    @ApiOperation(value = "根据行id删除单条信息", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/delete/id/{strId}", method = RequestMethod.GET)
    public DataResult<SimpleStringBean> deleteById(@PathVariable String strId) {
        return logCountsStreamingService.deleteById(strId);
    }

    @ApiOperation(value = "插入新记录", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/add", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> add(@RequestBody LogCountsStreaming logCountsStreaming) {
        return logCountsStreamingService.add(logCountsStreaming);
    }

    @ApiOperation(value = "更新记录", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/update", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> update(@RequestBody LogCountsStreaming logCountsStreaming) {
        return logCountsStreamingService.update(logCountsStreaming);
    }


}
