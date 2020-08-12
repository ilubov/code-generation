package com.i.lubov.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.i.lubov.comps.Pager;
import com.i.lubov.comps.R;
import com.i.lubov.dto.CodeColumnsDTO;
import com.i.lubov.dto.GenConfigDTO;
import com.i.lubov.entity.Columns;
import com.i.lubov.entity.GenConfig;
import com.i.lubov.entity.Tables;
import com.i.lubov.service.GenConfigService;
import com.i.lubov.service.GeneratorService;
import com.i.lubov.service.MySqlColumnsService;
import com.i.lubov.service.MySqlTablesService;
import com.i.lubov.util.BeanExtUtils;
import com.i.lubov.util.GenUtils;
import com.i.lubov.util.RedisUtils;
import com.i.lubov.vo.ColumnsVO;
import com.i.lubov.vo.TablesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"/代码生成器管理"})
@RestController
@RequestMapping({"/generator"})
public class GeneratorController {

    @Value("${generator.enabled:false}")
    private Boolean generatorEnabled;

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private MySqlTablesService mySqlTablesService;

    @Autowired
    private MySqlColumnsService mySqlColumnsService;

    @Autowired
    private GenConfigService genConfigService;

    public GeneratorController() {
    }

    @ApiOperation("根据id获取配置")
    @GetMapping({"/gen-config/{id}"})
    public R<Object> get(@PathVariable("id") @ApiParam(value = "配置项主键，固定值1", required = true) Long id) {
        GenConfig genConfig = this.genConfigService.getById(1L);
        return genConfig != null ? R.ok().data(BeanUtil.toBean(genConfig, GenConfigDTO.class)) : R.ok();
    }

    @ApiOperation("根据id更新配置")
    @PutMapping({"/{id}"})
    public R<Integer> update(@PathVariable("id") @ApiParam(value = "配置项主键，固定值1", required = true) Long id, @Validated({GenConfigDTO.Update.class}) @RequestBody GenConfigDTO genConfigDTO) {
        return this.genConfigService.updateById(BeanUtil.toBean(genConfigDTO, GenConfig.class)) ? R.ok() : R.error();
    }

    @ApiOperation("按查询条件分页获取Table列表")
    @GetMapping({"/tables"})
    public R<Object> tables(String tableName, @Validated Pager<TablesVO> pager) {
        IPage<Tables> page = this.mySqlTablesService.findTables(pager.build(Tables.class), tableName);
        return R.ok().total(page.getTotal()).data(BeanExtUtils.toBeans(page.getRecords(), TablesVO.class));
    }

    @ApiOperation("获取数据表对应的Column")
    @GetMapping({"/{tableName}/columns"})
    public R<Object> columns(@PathVariable("tableName") @ApiParam(value = "表名", required = true) String tableName) {
        List<Columns> columns = this.mySqlColumnsService.findColumns(tableName);
        return R.ok().data(BeanExtUtils.toBeans(columns, ColumnsVO.class));
    }

    @ApiOperation("生成uuid")
    @PostMapping({"/code/uuid"})
    public R<String> generatorForRedis(@RequestBody List<CodeColumnsDTO> codeColumnsDTOS) {
        if (!this.generatorEnabled) {
            return R.error();
        } else {
            String uuid = UUID.fastUUID().toString();
            RedisUtils.set(uuid, JSON.toJSONString(codeColumnsDTOS), 60L);
            return R.ok(uuid);
        }
    }

    @ApiOperation("以zip方式下载生成的代码")
    @GetMapping({"/code/{tableName}/{uuid}"})
    public void generator(@PathVariable("tableName") String tableName, @PathVariable("uuid") String uuid, GenConfig config, HttpServletResponse response) throws Exception {
        if (this.generatorEnabled) {
            if (!StrUtil.isEmpty(RedisUtils.get(uuid, String.class))) {
                List<CodeColumnsDTO> codeColumnsDTOS = JSONObject.parseArray(RedisUtils.get(uuid, String.class), CodeColumnsDTO.class);
                GenConfig genConfig = null;
                if (StrUtil.isNotEmpty(config.getAuthor())) {
                    genConfig = config;
                } else {
                    genConfig = this.genConfigService.getById(1L);
                }

                String tableComment = this.mySqlTablesService.getTable(tableName).getTableComment();
                byte[] data = this.generatorService.generator(codeColumnsDTOS, genConfig, tableName, tableComment);
                response.reset();
                response.setHeader("Content-Disposition", "attachment; filename=\"lubov-codes-" + genConfig.getModuleName() + ".zip\"");
                response.addHeader("Content-Length", "" + data.length);
                response.setContentType("application/octet-stream; charset=UTF-8");
                IOUtils.write(data, response.getOutputStream());
            }
        }
    }

    @ApiOperation("以zip方式下载生成的代码")
    @GetMapping({"/batch/module"})
    public void batchGeneratorModule(@RequestParam("artifactId") String artifactId, @RequestParam("groupId") String groupId, String version, HttpServletResponse response) throws Exception {
        if (this.generatorEnabled) {
            if (StrUtil.isEmpty(version)) {
                version = "1.0.0-SNAPSHOT";
            }

            String[] pomFiles = new String[]{"artifactId" + File.separator + "pom.xml", "artifactId" + File.separator + "artifactId-api" + File.separator + "pom.xml", "artifactId" + File.separator + "artifactId-boot" + File.separator + "pom.xml", "artifactId" + File.separator + "artifactId-impl" + File.separator + "pom.xml", "artifactId" + File.separator + "artifactId-spi" + File.separator + "pom.xml"};
            String[] moduleDirs = new String[]{"artifactId" + File.separator + "artifactId-api", "artifactId" + File.separator + "artifactId-boot", "artifactId" + File.separator + "artifactId-impl", "artifactId" + File.separator + "artifactId-spi", "artifactId"};
            String[] newModuleDirs = new String[]{artifactId + "-api", artifactId + "-boot", artifactId + "-impl", artifactId + "-spi", artifactId};
            File newZip = null;
            File tempDir = Files.createTempDir();

            try {
                ZipInputStream zipInputStream = new ZipInputStream(GeneratorController.class.getClassLoader().getResourceAsStream("template" + File.separator + "generator" + File.separator + "module" + File.separator + "artifactId.zip"));
                ZipUtil.unzip(zipInputStream, tempDir);
                String[] var11 = pomFiles;
                int var12 = pomFiles.length;

                for (int var13 = 0; var13 < var12; ++var13) {
                    String pom = var11[var13];
                    StringBuffer sb = new StringBuffer();
                    ImmutableList<String> lines = Files.asCharSource(new File(tempDir, pom), Charsets.UTF_8).readLines();
                    UnmodifiableIterator var17 = lines.iterator();

                    while (var17.hasNext()) {
                        String line = (String) var17.next();
                        sb.append(line.replaceAll("\\$\\{artifactId\\}", artifactId).replaceAll("\\$\\{groupId\\}", groupId).replaceAll("\\$\\{version\\}", version)).append(System.lineSeparator());
                    }

                    Files.asCharSink(new File(tempDir, pom), Charsets.UTF_8, new FileWriteMode[0]).write(sb);
                }

                for (int i = 0; i < moduleDirs.length; ++i) {
                    FileUtil.rename(new File(tempDir, moduleDirs[i]), newModuleDirs[i], false, true);
                }

                newZip = ZipUtil.zip(new File(tempDir + File.separator + artifactId));
                byte[] totalData = Files.toByteArray(newZip);
                response.reset();
                response.setHeader("Content-Disposition", "attachment; filename=\"" + artifactId + ".zip\"");
                response.addHeader("Content-Length", "" + totalData.length);
                response.setContentType("application/octet-stream; charset=UTF-8");
                IOUtils.write(totalData, response.getOutputStream());
            } finally {
                FileUtil.del(tempDir);
            }
        }
    }

    @ApiOperation("以zip方式下载生成的代码")
    @GetMapping({"/batch/code"})
    public void batchGenerator(String tables, GenConfig config, HttpServletResponse response) throws Exception {
        if (this.generatorEnabled) {
            List<CodeColumnsDTO> codeColumnsDTOS = null;
            String tableComment = null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(outputStream);

            try {
                Iterator var8 = Arrays.asList(tables.split(",")).iterator();

                while (var8.hasNext()) {
                    String tableName = (String) var8.next();
                    codeColumnsDTOS = BeanExtUtils.toBeans(this.mySqlColumnsService.findColumns(tableName), CodeColumnsDTO.class);
                    tableComment = this.mySqlTablesService.getTable(tableName).getTableComment();
                    GenUtils.generatorCode(codeColumnsDTOS, config, tableName, tableComment, zip);
                }
            } finally {
                IOUtils.closeQuietly(zip);
            }

            byte[] totalData = outputStream.toByteArray();
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"lubov-codes-" + config.getModuleName() + ".zip\"");
            response.addHeader("Content-Length", "" + totalData.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(totalData, response.getOutputStream());
        }
    }
}
