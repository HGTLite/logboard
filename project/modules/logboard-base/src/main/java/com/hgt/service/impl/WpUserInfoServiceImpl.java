package com.hgt.service.impl;

import io.yaoooo.common.constants.RestConst;
import io.yaoooo.domain.DataResult;
import io.yaoooo.domain.SimpleStringBean;
import io.yaoooo.entity.WpUserInfo;
import io.yaoooo.mapper.WpUserInfoMapper;
import io.yaoooo.service.WpUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/******************************************************************************
 * Created by  Yao  on  2016/7/17
 * README:
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
@Service("wpUserInfoService")
public class WpUserInfoServiceImpl implements WpUserInfoService {

    @Autowired
    WpUserInfoMapper wpUserInfoMapper;


    public DataResult<WpUserInfo> selectByPrimaryKey(String userId) {
        return new DataResult<>(wpUserInfoMapper.selectByPrimaryKey(userId));
    }

    public DataResult<List<WpUserInfo>> selectAll() {
        return new DataResult<>(wpUserInfoMapper.selectAll());
    }

    public DataResult<SimpleStringBean> addWpUserInfo(WpUserInfo wpUserInfo) {
        if (wpUserInfo == null) {
            return new DataResult<>(RestConst.ErrorCode.EMPTY_PARAM, "param is empty");
        } else {
            wpUserInfoMapper.addWpUserInfo(wpUserInfo);
            return new DataResult<>(new SimpleStringBean((wpUserInfo.getUserNickname() + " " + wpUserInfo.getUserEmail() + " 插入成功")));
        }
    }

    public DataResult<WpUserInfo> getUserInfo(String idOrEmail) {
//        WpUserInfo wpUserInfo;
//        wpUserInfo = wpUserInfoMapper.getUserInfo(idOrEmail);
//
//        if (wpUserInfo == null) {
//            return new DataResult(RestConst.ErrorCode.DATABASE_ERROR,"找不到此用户的信息");
//        } else {
//            return new DataResult(wpUserInfo);
//        }
        return new DataResult<>(wpUserInfoMapper.getUserInfo(idOrEmail));
    }


}
