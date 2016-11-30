package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.ExpStreaming;
import com.hgt.entity.LogBeats;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface LogBeatsService {

    DataResult<List<LogBeats>> findAll();

    DataResult<LogBeats> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(LogBeats obj);

    DataResult<SimpleStringBean> update(LogBeats obj);

}
