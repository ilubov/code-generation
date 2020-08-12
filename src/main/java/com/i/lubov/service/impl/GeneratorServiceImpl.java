package com.i.lubov.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

import com.i.lubov.dto.CodeColumnsDTO;
import com.i.lubov.entity.GenConfig;
import com.i.lubov.service.GeneratorService;
import com.i.lubov.util.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class GeneratorServiceImpl implements GeneratorService {
    public GeneratorServiceImpl() {
    }

    public byte[] generator(List<CodeColumnsDTO> codeColumnsDTOS, GenConfig genConfig, String tableName, String tableComment) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        try {
            GenUtils.generatorCode(codeColumnsDTOS, genConfig, tableName, tableComment, zip);
        } finally {
            IOUtils.closeQuietly(zip);
        }

        return outputStream.toByteArray();
    }
}

