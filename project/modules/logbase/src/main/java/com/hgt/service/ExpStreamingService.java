package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.ExpStreaming;
import com.hgt.entity.LogApps;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface ExpStreamingService {

    DataResult<List<ExpStreaming>> findAll();

    DataResult<ExpStreaming> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(ExpStreaming obj);

    DataResult<SimpleStringBean> update(ExpStreaming obj);

}
