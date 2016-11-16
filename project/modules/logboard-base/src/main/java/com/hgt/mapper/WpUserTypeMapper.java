package com.hgt.mapper;

import io.yaoooo.entity.WpUserType;

public interface WpUserTypeMapper {

    int deleteByPrimaryKey(Integer utypeId);

    int insert(WpUserType record);

    int insertSelective(WpUserType record);

    WpUserType selectByPrimaryKey(Integer utypeId);

    int updateByPrimaryKeySelective(WpUserType record);

    int updateByPrimaryKey(WpUserType record);
}