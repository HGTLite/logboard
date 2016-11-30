package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogBeats;
import com.hgt.entity.LogBeats;
import com.hgt.mapper.LogBeatsMapper;
import com.hgt.mapper.LogBeatsMapper;
import com.hgt.service.LogBeatsService;
import com.hgt.service.LogBeatsService;
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
public class LogBeatsServiceImpl implements LogBeatsService {

    @Autowired
    private LogBeatsMapper logBeatsMapper;

    @Override
    public DataResult<List<LogBeats>> findAll() {
        return new DataResult<>(logBeatsMapper.selectAll());
    }

    @Override
    public DataResult<LogBeats> findById(String strId) {
        return new DataResult<>(logBeatsMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = logBeatsMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(LogBeats obj) {
        int ret = logBeatsMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(LogBeats obj) {
        int ret = logBeatsMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
