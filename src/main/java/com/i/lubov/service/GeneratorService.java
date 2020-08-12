package com.i.lubov.service;

import com.i.lubov.dto.CodeColumnsDTO;
import com.i.lubov.entity.GenConfig;

import java.util.List;

public interface GeneratorService {
    byte[] generator(List<CodeColumnsDTO> codeColumnsDTOS, GenConfig genConfig, String tableName, String tableComment);
}
