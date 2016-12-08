package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogBeatsLost;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface LogBeatsLostService {

    DataResult<List<LogBeatsLost>> findAll();

    DataResult<LogBeatsLost> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(LogBeatsLost obj);

    DataResult<SimpleStringBean> update(LogBeatsLost obj);

}
