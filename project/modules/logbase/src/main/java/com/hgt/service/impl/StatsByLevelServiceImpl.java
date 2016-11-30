package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.StatsByLevel;
import com.hgt.mapper.StatsByLevelMapper;
import com.hgt.service.StatsByLevelService;
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
public class StatsByLevelServiceImpl implements StatsByLevelService {

    @Autowired
    private StatsByLevelMapper statsByLevelMapper;

    @Override
    public DataResult<List<StatsByLevel>> findAll() {
        return new DataResult<>(statsByLevelMapper.selectAll());
    }

    @Override
    public DataResult<StatsByLevel> findById(String strId) {
        return new DataResult<>(statsByLevelMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = statsByLevelMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(StatsByLevel obj) {
        int ret = statsByLevelMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(StatsByLevel obj) {
        int ret = statsByLevelMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
