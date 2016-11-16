package com.hgt.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.WpUserInfo;
import io.yaoooo.mapper.WpUserInfoMapper;
import io.yaoooo.service.WpUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:直接操作数据库wp_photos_info表的类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@Api(value = "已完成插入、查询，未完成修改、删除……", description = "关于数据库中用户信息操作的接口")
@RestController
public class WpUserInfoController {

    private final String BASE_URL = "users";

    @Autowired
    WpUserInfoService wpUserInfoService;
    @Autowired
    WpUserInfoMapper wpUserInfoMapper;


    @ApiOperation(value = "根据用户ID获取用户的详细信息", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户唯一ID，是32位字符串", required = true,
                    dataType = "String", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/id/{userId:.+}", method = RequestMethod.GET)
    public DataResult<WpUserInfo> selectByPrimaryKey(@PathVariable String userId) {
        return wpUserInfoService.selectByPrimaryKey(userId);
    }

    @ApiIgnore
    @ApiOperation(value = "获取所有用户的信息", notes = "无需输入参数，待分页")
    @RequestMapping(value = BASE_URL + "/all", method = RequestMethod.GET)
    public DataResult<List<WpUserInfo>> selectAll() {
        return wpUserInfoService.selectAll();
    }

    @ApiOperation(value = "获取所有用户的信息", notes = "获取所有用户的信息，无需输入参数，分页完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前第page页", required = true,
                    dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", required = true,
                    dataType = "int", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/all/{page}/{pageSize}", method = RequestMethod.GET)
    public DataResult<PageInfo> selectAllU(@PathVariable Integer page, @PathVariable Integer pageSize) {
        if (page != null && pageSize != null) {
            PageHelper.startPage(page, pageSize);
        }
        List<WpUserInfo> list = wpUserInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return new DataResult<>(pageInfo);
    }

    @ApiOperation(value = "向数据库插入用户信息的方法", notes = "已完成，不推荐使用，请用照片上传的方法，目前只能通过上传照片的途径增加新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wpUserInfo", value = "用户详细信息，邮箱不能重复，目前邮箱被用作唯一ID", required = true,
                    dataType = "WpPhotosInfo", paramType = "query")
    })
    @RequestMapping(value = BASE_URL + "/add", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> addWpUserInfo(@RequestBody WpUserInfo wpUserInfo) {
        return wpUserInfoService.addWpUserInfo(wpUserInfo);
    }

}
