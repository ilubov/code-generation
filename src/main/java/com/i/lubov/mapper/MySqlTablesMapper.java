package com.i.lubov.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.i.lubov.entity.Tables;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MySqlTablesMapper extends BaseMapper<Tables> {
    IPage<Tables> findTables(IPage<Tables> page, @Param("tableName") String tableName);

    Tables getTable(@Param("tableName") String tableName);
}
