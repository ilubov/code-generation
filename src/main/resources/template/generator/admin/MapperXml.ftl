<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.mapper.${className}Mapper">
    <resultMap id="${className}Result" type="${package}.entity.${className}">
    <#list columns as column>
        <result property="${column.camelCaseColumnName}" column="${column.columnName}" />
    </#list>
    </resultMap>

    <sql id="selectAllColumns">
        select
        <#list columns as column>
            ${column.columnName}<#if column_has_next>,</#if>
        </#list>
        from ${tableName}
    </sql>

    <update id="_updateById" parameterType="${package}.entity.${className}">
        update ${tableName}
        set
        <#list columns as column>
            ${column.columnName} = ${r"#{"}${column.camelCaseColumnName}${r"}"}<#if column_has_next>,</#if>
        </#list>
        WHERE id = ${r"#{"}id${r"}"}
    </update>

    <update id="_update" parameterType="${package}.entity.${className}">
        update ${tableName}
        <set>
        <#list columns as column>
            <#if column.columnType = 'String'>
                ${r'<if test="'}${column.camelCaseColumnName}${r' != null and '}${column.camelCaseColumnName}${r" != ''"}${r'">'}
            <#else>
                ${r'<if test="'}${column.camelCaseColumnName}${r' != null">'}
            </#if>
            ${column.columnName} = ${r"#{"}${column.camelCaseColumnName}${r"}"}<#if column_has_next>,</#if>
            ${r'</if>'}
        </#list>
        </set>
        WHERE id = ${r"#{"}id${r"}"}
    </update>
</mapper>
