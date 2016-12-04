package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogCountsStreaming;
import com.hgt.mapper.LogCountsStreamingMapper;
import com.hgt.service.LogCountsStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * README:
 * Created by Yao on 11/30/16.
 * =============================================================================
 * CHANGELOG:
 */
@Service
public class LogCountsStreamingServiceImpl implements LogCountsStreamingService {

    @Autowired
    private LogCountsStreamingMapper logCountsStreamingMapper;

    @Override
    public DataResult<List<LogCountsStreaming>> findAll() {
        return new DataResult<>(logCountsStreamingMapper.selectAll());
    }

    @Override
    public DataResult<LogCountsStreaming> findById(String strId) {
        return new DataResult<>(logCountsStreamingMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> updateAddCount(String strId, int intCount) {
//        int ret = logCountsStreamingMapper.updateAddCount(strId, intCount);
        return new DataResult<>(new SimpleStringBean("LOG_COUNTS_STREAMING表记录成功添加 " + intCount + " 条记录"));
    }

//        @Override
//    public DataResult<SimpleStringBean> updateAddCount(  int intCount) {
//        int ret = logCountsStreamingMapper.updateAddCount( intCount);
//        return new DataResult<>(new SimpleStringBean("LOG_COUNTS_STREAMING表记录成功加 " + intCount + " "));
//    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = logCountsStreamingMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(LogCountsStreaming obj) {
        int ret = logCountsStreamingMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(LogCountsStreaming obj) {
        int ret = logCountsStreamingMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
