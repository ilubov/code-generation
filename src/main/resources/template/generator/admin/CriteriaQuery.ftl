package ${package}.dto.criteria;

import io.swagger.annotations.ApiModel;
import com.i.lubov.annotation.Query;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if hasDate>
import java.util.Date;
</#if>

/**
 * ${className}CriteriaQuery
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@ApiModel
public class ${className}CriteriaQuery implements Serializable {

<#if blurry?? >
    /**
     * 模糊搜索字段
     */
    @ApiModelProperty("模糊搜索字段")
    @Query(blurry = "${blurry}")
    private String blurry;
</#if>

    /**
     * 排序
     */
    @ApiModelProperty("排序示例：sort = name:asc,createdTime:desc")
    @Query(sort = true)
    private String sort = "${sort!""}";

    /**
     * 分组示例
     */
    @ApiModelProperty("分组示例：group = name,age")
    @Query(group = true)
    private String group = "${group!""}";

<#if columns??>
    <#list columns as column>
        <#if column.camelCaseColumnName != 'deleted'>
            <#if column.queryType?? && column.queryType="BETWEEN">
                <#if column.columnComment != ''>

    /**
     * ${column.columnComment}
     */
                </#if>
    @ApiModelProperty("${column.columnComment}")
    @Query(type = Query.Type.GE, column = "${column.columnName}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ${column.columnType} ${column.opMap.start};

                <#if column.columnComment != ''>

    /**
     * ${column.columnComment}
     */
                </#if>
    @ApiModelProperty("${column.columnComment}")
    @Query(type = Query.Type.LE, column = "${column.columnName}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ${column.columnType} ${column.opMap.end};
            <#elseif column.queryType?? && column.queryType!="BLURRY">
                <#if column.columnComment != ''>

    /**
     * ${column.columnComment}
     */
                </#if>
    @ApiModelProperty("${column.columnComment}")
    @Query(type = Query.Type.${column.queryType}, column = "${column.columnName}")
    private ${column.columnType} ${column.camelCaseColumnName};
            <#else>
                <#if column.columnComment != ''>

    /**
     * ${column.columnComment}
     */
                </#if>
    @ApiModelProperty("${column.columnComment}")
    @Query(type = Query.Type.EQ, column = "${column.columnName}")
    private ${column.columnType} ${column.camelCaseColumnName};
            </#if>
        </#if>
    </#list>

    /**
     * 页号从1开始
     */
    @ApiModelProperty("页号从1开始")
    @Min(1)
    private Long page;

    /**
     * 分页大小
     */
    @ApiModelProperty("分页取值在1-500之间")
    @Min(1)
    @Max(500)
    private Long size;
</#if>
}
