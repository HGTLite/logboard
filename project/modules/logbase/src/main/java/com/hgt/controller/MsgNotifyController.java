package com.hgt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.MsgNotify;
import com.hgt.mapper.MsgNotifyMapper;
import com.hgt.service.MsgNotifyService;
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
@Api(value = "预警通知信息 val", description = "关于预警通知信息操作的接口")
@RestController
public class MsgNotifyController {

    private final String BASE_URL = "logb/beats";

    @Autowired
    MsgNotifyService msgNotifyService;

    @Autowired
    MsgNotifyMapper msgNotifyMapper;

    @ApiOperation(value = "根据行id获取单条信息", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/rid/{strId}", method = RequestMethod.GET)
    public DataResult<MsgNotify> findById(@PathVariable String strId) {
        return msgNotifyService.findById(strId);
    }

    @ApiOperation(value = "获取所有记录（不分页）", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/all", method = RequestMethod.GET)
    public DataResult<List<MsgNotify>> findAll() {
        return msgNotifyService.findAll();
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
        List<MsgNotify> list = msgNotifyMapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return new DataResult<>(pageInfo);
    }

    @ApiOperation(value = "根据行id删除单条信息", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/delete/id/{strId}", method = RequestMethod.GET)
    public DataResult<SimpleStringBean> deleteById(@PathVariable String strId) {
        return msgNotifyService.deleteById(strId);
    }


    @ApiOperation(value = "插入新记录", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/add", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> add(@RequestBody MsgNotify msgNotify) {
        return msgNotifyService.add(msgNotify);
    }


    @ApiOperation(value = "更新记录", notes = "已完成")
    @RequestMapping(value = BASE_URL + "/update", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> update(@RequestBody MsgNotify msgNotify) {
        return msgNotifyService.update(msgNotify);
    }

}
