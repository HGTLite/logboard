package com.hgt.service;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogApps;

import java.util.List;

/**
 * INTRO:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface LogAppsService {

    DataResult<LogApps>  findLogAppById(String laId);

    DataResult<List<LogApps>>  findAllLogApps();

    DataResult<SimpleStringBean> addLogApp(LogApps logApp);

    DataResult<SimpleStringBean> deleteLogApp(String laId);

}
