package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.LogBeatsNormal;
import com.hgt.mapper.LogBeatsNormalMapper;
import com.hgt.service.LogBeatsNormalService;
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
public class LogBeatsNormalServiceImpl implements LogBeatsNormalService {

    @Autowired
    private LogBeatsNormalMapper logBeatsNormalMapper;

    @Override
    public DataResult<List<LogBeatsNormal>> findAll() {
        return new DataResult<>(logBeatsNormalMapper.selectAll());
    }

    @Override
    public DataResult<LogBeatsNormal> findById(String strId) {
        return new DataResult<>(logBeatsNormalMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = logBeatsNormalMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(LogBeatsNormal obj) {
        int ret = logBeatsNormalMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(LogBeatsNormal obj) {
        int ret = logBeatsNormalMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
