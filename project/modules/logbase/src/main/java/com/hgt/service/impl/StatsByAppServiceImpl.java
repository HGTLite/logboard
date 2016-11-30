package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.StatsByApp;
import com.hgt.mapper.StatsByAppMapper;
import com.hgt.service.StatsByAppService;
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
public class StatsByAppServiceImpl implements StatsByAppService {

    @Autowired
    private StatsByAppMapper statsByAppMapper;

    @Override
    public DataResult<List<StatsByApp>> findAll() {
        return new DataResult<>(statsByAppMapper.selectAll());
    }

    @Override
    public DataResult<StatsByApp> findById(String strId) {
        return new DataResult<>(statsByAppMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = statsByAppMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(StatsByApp obj) {
        int ret = statsByAppMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(StatsByApp obj) {
        int ret = statsByAppMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
