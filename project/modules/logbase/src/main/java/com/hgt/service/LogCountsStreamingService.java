package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogCountsStreaming;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface LogCountsStreamingService {

    DataResult<SimpleStringBean>  updateAddCount(String strId, int intCount);
//    DataResult<SimpleStringBean>  updateAddCount( int intCount);

    DataResult<List<LogCountsStreaming>> findAll();

    DataResult<LogCountsStreaming> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(LogCountsStreaming obj);

    DataResult<SimpleStringBean> update(LogCountsStreaming obj);

}
