package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.MsgNotify;
import com.hgt.entity.StatsByApp;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface StatsByAppService {

    DataResult<List<StatsByApp>> findAll();

    DataResult<StatsByApp> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(StatsByApp obj);

    DataResult<SimpleStringBean> update(StatsByApp obj);

}
