package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogBeats;
import com.hgt.entity.MsgNotify;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface MsgNotifyService {

    DataResult<List<MsgNotify>> findAll();

    DataResult<MsgNotify> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(MsgNotify obj);

    DataResult<SimpleStringBean> update(MsgNotify obj);

}
