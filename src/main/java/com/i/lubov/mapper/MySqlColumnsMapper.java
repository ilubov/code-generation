package com.i.lubov.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

import com.i.lubov.entity.Columns;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MySqlColumnsMapper extends BaseMapper<Columns> {
    List<Columns> findColumns(String tableName);
}

