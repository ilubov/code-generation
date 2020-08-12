package com.i.lubov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import com.i.lubov.entity.Columns;
import com.i.lubov.mapper.MySqlColumnsMapper;
import com.i.lubov.service.MySqlColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MySqlColumnsServiceImpl extends ServiceImpl<MySqlColumnsMapper, Columns> implements MySqlColumnsService {
    @Autowired
    private MySqlColumnsMapper mySqlColumnsMapper;

    public MySqlColumnsServiceImpl() {
    }

    @Override
    public List<Columns> findColumns(String tableName) {
        return this.mySqlColumnsMapper.findColumns(tableName);
    }
}
