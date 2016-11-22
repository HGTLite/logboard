package com.hgt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogApps;
import com.hgt.mapper.LogAppsMapper;
import com.hgt.service.LogAppsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * README:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
@Api(value = "接入日志系统的第三方应用 val", description = "关于接入日志系统的第三方应用的接口")
@RestController
public class LogAppsController {

    private final String BASE_URL = "lb/apps";

    @Autowired
    LogAppsService logAppsService;

    @Autowired
    LogAppsMapper logAppsMapper;

    @ApiOperation(value = "根据行id获取单条信息", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/id/{laId}", method = RequestMethod.GET)
    public DataResult<LogApps> findLogAppById(@PathVariable String laId) {
        return logAppsService.findLogAppById(laId);
    }

    @ApiOperation(value = "获取所有接入的应用系统信息（不分页）", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/all", method = RequestMethod.GET)
    public DataResult<List<LogApps>> findAllLogApps() {
        return logAppsService.findAllLogApps();
    }


    @ApiOperation(value = "获取所有接入的应用系统信息（分页）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第pageNum页", required = true,
                    dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", required = true,
                    dataType = "int", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/all/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public DataResult<PageInfo> findAllLogAppsByPages(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<LogApps> list = logAppsMapper.selectAllLogApps();
        PageInfo pageInfo = new PageInfo(list);
        return new DataResult<>(pageInfo);
    }

    @ApiOperation(value = "获取接入应用总数", notes = "已完成")
    @RequestMapping(value = BASE_URL+"/counts",method = RequestMethod.GET)
    public DataResult<SimpleStringBean> getTableCounts(){
        return logAppsService.getTableCounts();
    }

    @ApiOperation(value = "插入新记录", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/add", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> addLogApp(@RequestBody LogApps logApp) {
        return logAppsService.addLogApp(logApp);
    }


    @ApiOperation(value = "根据行id删除单条信息", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/delete/id/{laId}", method = RequestMethod.GET)
    public DataResult<SimpleStringBean> deleteLogAppByRowId(@PathVariable String laId) {
        return logAppsService.deleteLogAppByRowId(laId);
    }

    @ApiOperation(value = "根据应用编号appCode删除单条信息", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/delete/code/{appCode}", method = RequestMethod.GET)
    public DataResult<SimpleStringBean> deleteLogAppByAppCode(@PathVariable String appCode) {
        return logAppsService.deleteLogAppByAppCode(appCode);
    }

    @ApiOperation(value = "更新记录", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/update", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> updateLogApp(@RequestBody LogApps logApp) {
        return logAppsService.updateLogApp(logApp);
    }




}
