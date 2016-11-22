package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogApps;
import com.hgt.mapper.LogAppsMapper;
import com.hgt.service.LogAppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * README:
 * Created by root on 11/17/16.
 * =============================================================================
 * CHANGELOG:
 */
@Service
public class LogAppsServiceImpl implements LogAppsService {

    @Autowired
    private LogAppsMapper logAppsMapper;

    public DataResult<LogApps> findLogAppById(String laId) {

        return new DataResult<>(logAppsMapper.selectLogAppsById(laId));

    }

    public DataResult<List<LogApps>> findAllLogApps() {

        return new DataResult<>(logAppsMapper.selectAllLogApps());

    }

    public DataResult<SimpleStringBean> addLogApp(LogApps logApp) {
        int count = logAppsMapper.insertLogApps(logApp);
        return new DataResult<>(new SimpleStringBean("成功插入： " + count + " 条记录"));
    }

    public DataResult<SimpleStringBean> deleteLogAppByRowId(String laId) {
        int count = logAppsMapper.deleteLogAppByRowId(laId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + count + " 条记录"));
    }

    public DataResult<SimpleStringBean> deleteLogAppByAppCode(String appCode) {
        int count = logAppsMapper.deleteLogAppByAppCode(appCode);
        return new DataResult<>(new SimpleStringBean("成功删除： " + count + " 条记录"));
    }

    public DataResult<SimpleStringBean> updateLogApp(LogApps logApps) {
        int count = logAppsMapper.updateByPrimaryKey(logApps);
        return new DataResult<>(new SimpleStringBean("成功更新： " + count + " 条记录"));    }
}
