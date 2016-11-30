package com.hgt.service.impl;

import com.hgt.domain.DataResult;
import com.hgt.domain.SimpleStringBean;
import com.hgt.entity.MsgNotify;
import com.hgt.mapper.MsgNotifyMapper;
import com.hgt.service.MsgNotifyService;
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
public class MsgNotifyServiceImpl implements MsgNotifyService {

    @Autowired
    private MsgNotifyMapper msgNotifyMapper;

    @Override
    public DataResult<List<MsgNotify>> findAll() {
        return new DataResult<>(msgNotifyMapper.selectAll());
    }

    @Override
    public DataResult<MsgNotify> findById(String strId) {
        return new DataResult<>(msgNotifyMapper.selectByPrimaryKey(strId));
    }

    @Override
    public DataResult<SimpleStringBean> deleteById(String strId) {
        int ret = msgNotifyMapper.deleteByPrimaryKey(strId);
        return new DataResult<>(new SimpleStringBean("成功删除： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> add(MsgNotify obj) {
        int ret = msgNotifyMapper.insert(obj);
        return new DataResult<>(new SimpleStringBean("成功插入： " + ret + " 条记录"));
    }

    @Override
    public DataResult<SimpleStringBean> update(MsgNotify obj) {
        int ret = msgNotifyMapper.updateByPrimaryKey(obj);
        return new DataResult<>(new SimpleStringBean("成功更新： " + ret + " 条记录"));
    }
}
