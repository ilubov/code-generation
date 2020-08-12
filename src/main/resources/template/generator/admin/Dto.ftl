package ${package}.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if hasNullable>
import javax.validation.constraints.NotNull;
</#if>
import java.io.Serializable;
<#if hasDate>
import java.util.Date;
</#if>

/**
 * ${className}DTO
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@ApiModel
public class ${className}DTO implements Serializable {
<#if columns??>
    <#list columns as column>
        <#if column.columnComment != ''>

    /**
     * ${column.columnComment}
     */
        </#if>
        <#if column.camelCaseColumnName != 'id'>
            <#if column.camelCaseColumnName != 'updateTime'
            && column.camelCaseColumnName != 'createUser'
            && column.camelCaseColumnName != 'updateUser'
            && column.camelCaseColumnName != 'createTime'
            && column.camelCaseColumnName != 'deleted'>
    @ApiModelProperty("${column.columnComment}")
            <#else>
    @JsonIgnore
    @ApiModelProperty(value = "${column.columnComment}", hidden = true)
            </#if>
        </#if>
        <#if column.columnKey = 'PRI'>
    @JsonIgnore
    @ApiModelProperty(value = "${column.columnComment}", hidden = true)
        </#if>
    private ${column.columnType} ${column.camelCaseColumnName};
    </#list>

    public @interface Create {}
    public @interface Update {}
</#if>
}
