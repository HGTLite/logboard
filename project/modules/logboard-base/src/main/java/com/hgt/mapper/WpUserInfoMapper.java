package com.hgt.mapper;

import io.yaoooo.entity.WpUserInfo;

import java.util.List;

public interface WpUserInfoMapper {

    List<WpUserInfo> selectAll();

    void addWpUserInfo(WpUserInfo wpUserInfo);


    WpUserInfo getUserInfo(String idOrEmail);


    //===========================================================
    int deleteByPrimaryKey(String userId);

    int insert(WpUserInfo record);

    int insertSelective(WpUserInfo record);

    WpUserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(WpUserInfo record);

    int updateByPrimaryKey(WpUserInfo record);
}