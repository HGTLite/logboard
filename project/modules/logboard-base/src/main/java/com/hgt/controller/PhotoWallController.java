package com.hgt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.yaoooo.common.constants.URLConstants;
import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.PhotosInfoSimple;
import io.yaoooo.entity.WpPhotosInfo;
import io.yaoooo.service.WpPhotosInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README: 直接面向照片墙业务的Controller类
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@Api(value = "关于照片墙操作的接口，随机获取16张照片，待改进", description = "关于照片墙操作的接口")
@RestController
public class PhotoWallController {

    private final String BASE_URL = "photos";

    @Autowired
    WpPhotosInfoService wpPhotosInfoService;

    /**
     * @return 照片墙16张照片的精简信息
     */
    @ApiOperation(value="获取照片墙列表", notes="无需输入参数")
    @RequestMapping(value = BASE_URL + "/wall", method = RequestMethod.GET)
    public DataResult<List<PhotosInfoSimple>> queryLatestPhotoWallFinal() {

        DataResult<List<WpPhotosInfo>> photosList = wpPhotosInfoService.queryLatestPhotoWall();

        List<PhotosInfoSimple> photosSimpleList = new ArrayList<PhotosInfoSimple>();

        for (WpPhotosInfo p : photosList.getData()) {

            PhotosInfoSimple s = new PhotosInfoSimple();

            //拼凑照片URL
            String photoThumbFullPath = URLConstants.PHOTOS_SERVER_IP + p.getPhotoThumbPath();
            String photoOriginalFullPath = URLConstants.PHOTOS_SERVER_IP + p.getPhotoOriginalPath();

            s.setPhotoId(p.getPhotoId());
            s.setPhotoThumbPath(photoThumbFullPath);
            s.setPhotoOriginalPath(photoOriginalFullPath);
            s.setPhotoTitle(p.getPhotoTitle());
            s.setPhotoDesc(p.getPhotoDesc());
            s.setPhotoTags(p.getPhotoTags());
            s.setUploadBy(p.getUploadBy());
            s.setUploadDate(p.getUploadDate());
            s.setModuleId(p.getModuleId());
            s.setItemId(p.getItemId());
            s.setRemarksNum(p.getRemarksNum());
            s.setLikesNum(p.getLikesNum());

            photosSimpleList.add(s);
        }
        return new DataResult<>(photosSimpleList);
    }
}
