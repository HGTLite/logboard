package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.ExpStreaming;
import com.hgt.mapper.ExpStreamingMapper;
import com.hgt.mapper.LogAppsMapper;
import com.hgt.service.ExpStreamingService;
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
public class ExpStreamingServiceImpl implements ExpStreamingService {

    @Autowired
    private ExpStreamingMapper expStreamingMapper;

    @Override
    public DataResult<List<ExpStreaming>> findAll() {
        return new DataResult<>(expStreamingMapper.selectAll());
    }

    @Override
    public DataResult<ExpStreaming> findById(String strId) {
        return new DataResult<>(expStreamingMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = expStreamingMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(ExpStreaming obj) {
        int ret = expStreamingMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(ExpStreaming obj) {
        int ret = expStreamingMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
