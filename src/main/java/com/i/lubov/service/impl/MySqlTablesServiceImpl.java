package com.i.lubov.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i.lubov.entity.Tables;
import com.i.lubov.mapper.MySqlTablesMapper;
import com.i.lubov.service.MySqlTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MySqlTablesServiceImpl extends ServiceImpl<MySqlTablesMapper, Tables> implements MySqlTablesService {
    @Autowired
    private MySqlTablesMapper mySqlTablesMapper;

    public MySqlTablesServiceImpl() {
    }

    @Override
    public IPage<Tables> findTables(IPage<Tables> page, String tableName) {
        return this.mySqlTablesMapper.findTables(page, tableName);
    }

    @Override
    public Tables getTable(String tableName) {
        return this.mySqlTablesMapper.getTable(tableName);
    }
}

