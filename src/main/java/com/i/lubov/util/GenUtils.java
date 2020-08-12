package com.i.lubov.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateException;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.TemplateConfig.ResourceMode;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.i.lubov.dto.CodeColumnsDTO;
import com.i.lubov.entity.GenConfig;
import com.i.lubov.enums.QueryType;
import org.springframework.util.ObjectUtils;

public class GenUtils {

    public GenUtils() {}

    private static List<String> getAdminTemplateNames() {
        List<String> templateNames = Lists.newArrayList();
        templateNames.add("Api");
        templateNames.add("Controller");
        templateNames.add("CriteriaQuery");
        templateNames.add("Dto");
        templateNames.add("Entity");
        templateNames.add("Mapper");
        templateNames.add("MapperXml");
        templateNames.add("Service");
        templateNames.add("ServiceImpl");
        templateNames.add("Vo");
        return templateNames;
    }

    private static List<String> getFrontTemplateNames() {
        List<String> templateNames = Lists.newArrayList();
        templateNames.add("api");
        templateNames.add("index");
        templateNames.add("form");
        return templateNames;
    }

    public static void generatorCode(List<CodeColumnsDTO> codeColumnsDTOS, GenConfig genConfig, String tableName, String tableComment, ZipOutputStream zip) {
        Map<String, Object> templateParams = Maps.newHashMap();
        templateParams.put("package", genConfig.getPack());
        templateParams.put("moduleName", genConfig.getModuleName());
        templateParams.put("author", genConfig.getAuthor());
        templateParams.put("date", DateUtil.format(new Date(), "yyyy/MM/dd"));
        templateParams.put("tableName", tableName);
        templateParams.put("serviceName", genConfig.getServiceName());
        templateParams.put("tableComment", tableComment);
        String className = StringUtils.toCapitalizeCamelCase(tableName);
        String hyphenTableName = tableName.replaceAll("_", "-");
        hyphenTableName = hyphenTableName + 's';
        if (StrUtil.isNotEmpty(genConfig.getPrefix())) {
            className = StringUtils.toCapitalizeCamelCase(StrUtil.removePrefix(tableName, genConfig.getPrefix()));
            hyphenTableName = StrUtil.removePrefix(tableName, genConfig.getPrefix()).replaceAll("_", "-");
            hyphenTableName = hyphenTableName.replaceFirst("-", hyphenTableName);
            hyphenTableName = hyphenTableName + 's';
        }

        List<String> blurryList = codeColumnsDTOS.stream()
                .filter((blurry) -> QueryType.BLURRY.toString().equals(blurry.getQueryType()))
                .map(CodeColumnsDTO::getColumnName).collect(Collectors.toList());
        String blurryStr = String.join(",", blurryList);
        templateParams.put("className", className);
        templateParams.put("camelCaseClassName", StringUtils.toLowerFirst(className));
        templateParams.put("hyphenTableName", hyphenTableName);
        templateParams.put("hasDate", false);
        templateParams.put("hasBigDecimal", false);
        templateParams.put("hasNullable", false);
        templateParams.put("auto", false);
        templateParams.put("hasQuery", false);
        templateParams.put("blurry", blurryStr);
        templateParams.put("sort", (Object) null);
        templateParams.put("group", (Object) null);
        List<Map<String, Object>> columns = Lists.newArrayList();
        QueryType queryType = null;
        Iterator var12 = codeColumnsDTOS.iterator();

        String colType;
        while (var12.hasNext()) {
            CodeColumnsDTO column = (CodeColumnsDTO) var12.next();
            Map<String, Object> listMap = Maps.newHashMap();
            listMap.put("columnComment", column.getColumnComment());
            listMap.put("columnKey", column.getColumnKey());
            colType = ColUtils.cloToJava(column.getDataType());
            String camelCaseColumnName = StrUtil.toCamelCase(column.getColumnName());
            if ("PRI".equals(column.getColumnKey())) {
                templateParams.put("camelCaseColumnName", camelCaseColumnName);
            }
            if ("Date".equals(colType)) {
                templateParams.put("hasDate", true);
            }
            if ("BigDecimal".equals(colType)) {
                templateParams.put("hasBigDecimal", true);
            }
            if ("YES".equals(column.getIsNullable())) {
                templateParams.put("hasNullable", true);
            }
            if ("auto_increment".equals(column.getExtra())) {
                templateParams.put("auto", true);
            }
            queryType = QueryType.ofEnum(column.getQueryType());
            if (queryType == QueryType.BETWEEN) {
                Map<String, String> opMap = Maps.newHashMap();
                opMap.put("start", "start" + camelCaseColumnName);
                opMap.put("end", "end" + camelCaseColumnName);
                listMap.put("opMap", opMap);
            }
            if (queryType != null) {
                listMap.put("queryType", queryType.getDesc());
            }
            listMap.put("columnType", colType);
            listMap.put("columnName", column.getColumnName());
            listMap.put("isNullable", column.getIsNullable());
            listMap.put("columnShow", column.getColumnShow());
            listMap.put("camelCaseColumnName", camelCaseColumnName);
            columns.add(listMap);
        }
        templateParams.put("columns", columns);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", ResourceMode.CLASSPATH));
        Template template;
        String filePath;
        // backend
        List<String> templates = getAdminTemplateNames();
        Iterator var20 = templates.iterator();
        while (var20.hasNext()) {
            colType = (String) var20.next();
            template = engine.getTemplate("generator/admin/" + colType + ".ftl");
            filePath = getAdminFilePath(colType, genConfig, className);
            assert filePath != null;
            genZipFile(filePath, template, templateParams, zip);
        }
        // frontend
        /*templates = getFrontTemplateNames();
        var20 = templates.iterator();
        while (var20.hasNext()) {
            colType = (String) var20.next();
            template = engine.getTemplate("generator/front/" + colType + ".ftl");
            filePath = getFrontFilePath(colType, genConfig, templateParams.get("camelCaseClassName").toString());
            assert filePath != null;
            genZipFile(filePath, template, templateParams, zip);
        }*/
    }

    private static String getAdminFilePath(String templateName, GenConfig genConfig, String className) {
        String packagePath = "backend" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        String resourcePath = "backend" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        if (!ObjectUtils.isEmpty(genConfig.getPack())) {
            packagePath = packagePath + genConfig.getPack().replace(".", File.separator) + File.separator;
        }
        String adminFilePath = null;
        if (StrUtil.isNotBlank(templateName)) {
            switch (templateName) {
                case "Api":
                    adminFilePath = packagePath + "api" + File.separator + className + "Api.java";
                    break;
                case "Controller":
                    adminFilePath = packagePath + "controller" + File.separator + className + "Controller.java";
                    break;
                case "CriteriaQuery":
                    adminFilePath = packagePath + "dto" + File.separator + "criteria" + File.separator + className + "CriteriaQuery.java";
                    break;
                case "Dto":
                    adminFilePath = packagePath + "dto" + File.separator + className + "DTO.java";
                    break;
                case "Entity":
                    adminFilePath = packagePath + "entity" + File.separator + className + ".java";
                    break;
                case "Mapper":
                    adminFilePath = packagePath + "mapper" + File.separator + className + "Mapper.java";
                    break;
                case "MapperXml":
                    adminFilePath = resourcePath + "mapper" + File.separator + genConfig.getModuleName() + File.separator + className + "Mapper.xml";
                    break;
                case "Service":
                    adminFilePath = packagePath + "service" + File.separator + className + "Service.java";
                    break;
                case "ServiceImpl":
                    adminFilePath = packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
                    break;
                case "Vo":
                    adminFilePath = packagePath + "vo" + File.separator + className + "VO.java";
                    break;
                default:
                    break;
            }
        }
        return adminFilePath;
    }

    private static String getFrontFilePath(String templateName, GenConfig genConfig, String apiName) {
        String path = "frontend" + File.separator + genConfig.getPath();
        String apiPath = "frontend" + File.separator + genConfig.getPath();
        String frontFilePath = null;
        switch (templateName) {
            case "api":
                frontFilePath = apiPath + File.separator + apiName + ".js";
                break;
            case "index":
                frontFilePath = path + File.separator + apiName + File.separator + "index.vue";
                break;
            case "form":
                frontFilePath = path + File.separator + apiName + File.separator + "form.vue";
                break;
            default:
                break;
        }
        return frontFilePath;
    }

    private static void genZipFile(String filePath, Template template, Map<String, Object> map, ZipOutputStream zip) {
        try {
            zip.putNextEntry(new ZipEntry(filePath));
            template.render(map, zip);
            zip.closeEntry();
        } catch (IOException | TemplateException var5) {
            throw new RuntimeException(var5);
        }
    }
}
