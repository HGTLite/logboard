package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.StatsByApp;
import com.hgt.entity.StatsByLevel;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface StatsByLevelService {

    DataResult<List<StatsByLevel>> findAll();

    DataResult<StatsByLevel> findById(String strId);

    DataResult<SimpleStringBean> deleteById(String strId);

    DataResult<SimpleStringBean> add(StatsByLevel obj);

    DataResult<SimpleStringBean> update(StatsByLevel obj);

}
