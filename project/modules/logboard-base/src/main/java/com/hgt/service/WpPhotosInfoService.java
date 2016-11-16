package com.hgt.service;

import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.WpPhotosInfo;

import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public interface WpPhotosInfoService {


    DataResult<List<WpPhotosInfo>> queryLatestPhotoWall();

    DataResult<WpPhotosInfo> selectByPrimaryKey(String photoId);

    DataResult<List<WpPhotosInfo>> selectAll();

    DataResult<SimpleStringBean> addWpPhotosInfo(WpPhotosInfo wpPhotosInfo);

    DataResult<List<WpPhotosInfo>> getPhotosByUser(String userInfo);



}
