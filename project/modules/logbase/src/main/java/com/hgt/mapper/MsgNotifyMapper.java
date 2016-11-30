package com.hgt.mapper;

import com.hgt.entity.LogBeats;
import com.hgt.entity.MsgNotify;

import java.util.List;

public interface MsgNotifyMapper {

    List<MsgNotify> selectAll();

    int deleteByPrimaryKey(String msgRid);

    int insert(MsgNotify record);

    MsgNotify selectByPrimaryKey(String msgRid);

    int updateByPrimaryKey(MsgNotify record);

    //========================================

    int insertSelective(MsgNotify record);

    int updateByPrimaryKeySelective(MsgNotify record);

}