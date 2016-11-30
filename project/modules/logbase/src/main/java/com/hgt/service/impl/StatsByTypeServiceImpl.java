package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.StatsByType;
import com.hgt.mapper.StatsByTypeMapper;
import com.hgt.service.StatsByTypeService;
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
public class StatsByTypeServiceImpl implements StatsByTypeService {

    @Autowired
    private StatsByTypeMapper statsByTypeMapper;

    @Override
    public DataResult<List<StatsByType>> findAll() {
        return new DataResult<>(statsByTypeMapper.selectAll());
    }

    @Override
    public DataResult<StatsByType> findById(String strId) {
        return new DataResult<>(statsByTypeMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = statsByTypeMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(StatsByType obj) {
        int ret = statsByTypeMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(StatsByType obj) {
        int ret = statsByTypeMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
