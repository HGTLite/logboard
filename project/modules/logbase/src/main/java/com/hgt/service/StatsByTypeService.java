package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.StatsByApp;
import com.hgt.entity.StatsByType;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface StatsByTypeService {

    DataResult<List<StatsByType>> findAll();

    DataResult<StatsByType> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(StatsByType obj);

    DataResult<SimpleStringBean> update(StatsByType obj);

}
