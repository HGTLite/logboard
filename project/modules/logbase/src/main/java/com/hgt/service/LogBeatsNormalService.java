package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogBeatsNormal;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface LogBeatsNormalService {

    DataResult<List<LogBeatsNormal>> findAll();

    DataResult<LogBeatsNormal> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(LogBeatsNormal obj);

    DataResult<SimpleStringBean> update(LogBeatsNormal obj);

}
