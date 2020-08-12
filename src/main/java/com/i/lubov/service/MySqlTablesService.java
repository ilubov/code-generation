package com.i.lubov.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.i.lubov.entity.Tables;
import org.apache.ibatis.annotations.Param;

public interface MySqlTablesService extends IService<Tables> {
    IPage<Tables> findTables(IPage<Tables> page, @Param("tableName") String tableName);

    Tables getTable(@Param("tableName") String tableName);
}

