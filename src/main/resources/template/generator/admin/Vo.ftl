package ${package}.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if hasDate>
import java.util.Date;
</#if>

/**
 * ${className}VO
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@ApiModel
public class ${className}VO implements Serializable {
<#if columns??>
    <#list columns as column>
        <#if column.camelCaseColumnName != 'updateTime'
            && column.camelCaseColumnName != 'createUser'
            && column.camelCaseColumnName != 'updateUser'
            && column.camelCaseColumnName != 'deleted'>
        <#if column.columnComment != ''>

    /**
     * ${column.columnComment}
     */
        </#if>
    @ApiModelProperty("${column.columnComment}")
    private ${column.columnType} ${column.camelCaseColumnName};
        </#if>
    </#list>
</#if>
}
