package com.hgt.mapper;

import com.hgt.entity.MsgNotify;

public interface MsgNotifyMapper {


    //========================================
    int deleteByPrimaryKey(String msgRid);

    int insert(MsgNotify record);

    int insertSelective(MsgNotify record);

    MsgNotify selectByPrimaryKey(String msgRid);

    int updateByPrimaryKeySelective(MsgNotify record);

    int updateByPrimaryKey(MsgNotify record);
}