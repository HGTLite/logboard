package com.hgt.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.yaoooo.common.constants.URLConstants;
import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.WpPhotosInfo;
import io.yaoooo.mapper.WpPhotosInfoMapper;
import io.yaoooo.service.WpPhotosInfoService;
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
@Api(value = "已完成插入、查询，未完成修改、删除……", description = "关于数据库中照片信息操作的接口")
@RestController
public class WpPhotosInfoController {

    private final String BASE_URL = "photos";

    @Autowired
    public WpPhotosInfoService wpPhotosInfoService;

    @Autowired
    public WpPhotosInfoMapper wpPhotosInfoMapper;

    @ApiOperation(value = "返回照片墙16张照片的完整信息", notes = "无需输入参数")
    @RequestMapping(value = BASE_URL + "/wall/all", method = RequestMethod.GET)
    public DataResult<List<WpPhotosInfo>> queryLatestPhotoWall() {

        return wpPhotosInfoService.queryLatestPhotoWall();
    }

    @ApiIgnore
    @ApiOperation(value = "获取某用户的所有照片", notes = "不推荐，获取某个用户上传的所有照片信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfo", value = "用户唯一标识符，暂时只能用邮箱", required = true,
                    dataType = "String", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/user/{userInfo:.+}", method = RequestMethod.GET)
    public DataResult<List<WpPhotosInfo>> getPhotosByUser(@PathVariable String userInfo) {
        return wpPhotosInfoService.getPhotosByUser(userInfo);
    }


    @ApiOperation(value = "获取某用户的所有照片", notes = "待分页，获取某个用户上传的所有照片信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfo", value = "用户唯一标识符，暂时只能用邮箱", required = true,
                    dataType = "String", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/user/{userInfo:.+}/{page}/{pageSize}", method = RequestMethod.GET)
    public DataResult<PageInfo> getPhotosByUser(@PathVariable String userInfo, @PathVariable Integer page, @PathVariable Integer pageSize) {
        if (page != null && pageSize != null) {
            PageHelper.startPage(page, pageSize);
        }
        List<WpPhotosInfo> list = wpPhotosInfoMapper.getPhotosByUser(userInfo);
        for (WpPhotosInfo p : list) {
            p.setPhotoThumbPath(URLConstants.PHOTOS_SERVER_IP + p.getPhotoThumbPath());
            p.setPhotoOriginalPath(URLConstants.PHOTOS_SERVER_IP + p.getPhotoOriginalPath());
        }
        PageInfo pageInfo = new PageInfo(list);
        return new DataResult<>(pageInfo);
    }

    @ApiOperation(value = "根据photoID获取单张照片的完整信息", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "photoId", value = "照片唯一ID，是32位字符串", required = true,
                    dataType = "String", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/id/{photoId}", method = RequestMethod.GET)
    public DataResult<WpPhotosInfo> selectByPrimaryKey(@PathVariable String photoId) {
        return wpPhotosInfoService.selectByPrimaryKey(photoId);
    }

    @ApiIgnore
    @ApiOperation(value = "获取所有照片的信息", notes = "不推荐，获取所有照片的信息，无需输入参数，不分页")
    @RequestMapping(value = BASE_URL + "/all", method = RequestMethod.GET)
    public DataResult<List<WpPhotosInfo>> selectAll() {

        return wpPhotosInfoService.selectAll();
    }


    @ApiIgnore
    @ApiOperation(value = "获取所有照片的信息", notes = "获取所有照片的信息，无需输入参数，分页完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前第page页", required = true,
                    dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的条数", required = true,
                    dataType = "int", paramType = "path")
    })
    @RequestMapping(value = BASE_URL + "/all/{page}/{pageSize}", method = RequestMethod.GET)
    public DataResult<PageInfo> selectAllP(@PathVariable Integer page, @PathVariable Integer pageSize) {
        if (page != null && pageSize != null) {
            PageHelper.startPage(page, pageSize);
        }
        List<WpPhotosInfo> list = wpPhotosInfoMapper.selectAll();
        for (WpPhotosInfo p : list) {
            p.setPhotoThumbPath(URLConstants.PHOTOS_SERVER_IP + p.getPhotoThumbPath());
            p.setPhotoOriginalPath(URLConstants.PHOTOS_SERVER_IP + p.getPhotoOriginalPath());
        }
        PageInfo pageInfo = new PageInfo(list);
        return new DataResult<>(pageInfo);
    }


    @ApiOperation(value = "向数据库插入照片信息的方法（不能添加文件）", notes = "已完成，不推荐使用，请用照片上传的方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wpPhotosInfo", value = "照片详细信息，标题必填，其他选填", required = true,
                    dataType = "WpPhotosInfo", paramType = "query")
    })
    @RequestMapping(value = BASE_URL + "/add", method = RequestMethod.POST)
    public DataResult<SimpleStringBean> addWpPhotosInfo(@RequestBody WpPhotosInfo wpPhotosInfo) {
        return wpPhotosInfoService.addWpPhotosInfo(wpPhotosInfo);
    }

}
