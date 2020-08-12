package ${package}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if hasDate>
import java.util.Date;
</#if>

/**
 * ${tableName}
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@TableName("${tableName}")
public class ${className} extends Model<${className}> implements Serializable {
<#if columns??>
    <#list columns as column>
        <#if column.columnComment != ''>

    /**
     * ${column.columnComment}
     */
        </#if>
        <#if column.columnKey = 'PRI'>
    @TableId(value = "${column.columnName}", type = IdType.AUTO)
        <#elseif column.columnName == 'create_user'>
    @TableField(value = "${column.columnName}", fill = FieldFill.INSERT)
        <#elseif column.columnName == 'update_user'>
    @TableField(value = "${column.columnName}", fill = FieldFill.INSERT_UPDATE)
        <#elseif column.columnName == 'deleted'>
    @TableLogic
    @TableField("${column.columnName}")
        <#else>
    @TableField("${column.columnName}")
        </#if>
    private ${column.columnType} ${column.camelCaseColumnName};
    </#list>
</#if>
}
