package com.hgt.mapper;

import io.yaoooo.entity.WpUserRights;

public interface WpUserRightsMapper {

    int deleteByPrimaryKey(Integer rightsId);

    int insert(WpUserRights record);

    int insertSelective(WpUserRights record);

    WpUserRights selectByPrimaryKey(Integer rightsId);

    int updateByPrimaryKeySelective(WpUserRights record);

    int updateByPrimaryKey(WpUserRights record);
}