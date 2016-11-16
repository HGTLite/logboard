package com.hgt.service;

import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.WpUserInfo;

import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public interface WpUserInfoService {

    DataResult<WpUserInfo> selectByPrimaryKey(String userId);

    DataResult<List<WpUserInfo>> selectAll();

    DataResult<SimpleStringBean> addWpUserInfo(WpUserInfo wpUserInfo);

    DataResult<WpUserInfo> getUserInfo(String idOrEmail);



}
