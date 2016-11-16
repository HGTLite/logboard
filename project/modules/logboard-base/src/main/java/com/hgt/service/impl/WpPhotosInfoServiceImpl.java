package com.hgt.service.impl;

import io.yaoooo.common.constants.RestConst;
import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.WpPhotosInfo;
import io.yaoooo.mapper.WpPhotosInfoMapper;
import io.yaoooo.service.WpPhotosInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@Service("wpPhotosInfoService")
public class WpPhotosInfoServiceImpl implements WpPhotosInfoService {

    @Autowired
    WpPhotosInfoMapper wpPhotosInfoMapper;

    public DataResult<List<WpPhotosInfo>> queryLatestPhotoWall() {
        return new DataResult<>(wpPhotosInfoMapper.queryLatestPhotoWall());
    }

    public DataResult<WpPhotosInfo> selectByPrimaryKey(String photoId) {
        return new DataResult<>(wpPhotosInfoMapper.selectByPrimaryKey(photoId));
    }

    public DataResult<List<WpPhotosInfo>> selectAll() {

        return new DataResult<>(wpPhotosInfoMapper.selectAll());

    }


//    public DataResult<List<WpPhotosInfo>> selectAllPhotos() {
//
//        return new DataResult<>(wpPhotosInfoMapper.selectAll());
//
//    }

    public DataResult<SimpleStringBean> addWpPhotosInfo(WpPhotosInfo wpPhotosInfo) {
        if (wpPhotosInfo == null) {
            return new DataResult<>(RestConst.ErrorCode.EMPTY_PARAM, "param is empty");
        } else {
            wpPhotosInfoMapper.addWpPhotosInfo(wpPhotosInfo);
            return new DataResult<>(new SimpleStringBean((wpPhotosInfo.getPhotoTitle() + " " + wpPhotosInfo.getPhotoDesc() + " 插入成功")));
        }
    }

    public DataResult<List<WpPhotosInfo>> getPhotosByUser(String userInfo){
        return  new DataResult<>(wpPhotosInfoMapper.getPhotosByUser(userInfo));
    }







}
