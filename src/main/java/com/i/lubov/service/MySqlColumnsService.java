package com.i.lubov.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.i.lubov.entity.Columns;

import java.util.List;

public interface MySqlColumnsService extends IService<Columns> {
    List<Columns> findColumns(String tableName);
}
