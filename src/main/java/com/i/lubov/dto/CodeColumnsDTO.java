package com.i.lubov.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;

@ApiModel
public class CodeColumnsDTO implements Serializable {
    @ApiModelProperty(
            value = "字段名称",
            required = true
    )
    private String columnName;
    @ApiModelProperty(
            value = "允许空值 YES NO",
            required = true
    )
    private String isNullable;
    @ApiModelProperty(
            value = "字段类型bigint、varchar、bit等",
            required = true
    )
    private String dataType;
    @ApiModelProperty(
            value = "数据库字段注释",
            required = true
    )
    private String columnComment;
    @ApiModelProperty(
            value = "主键、外键标记PRI、UNI、空",
            required = true
    )
    private String columnKey;
    @ApiModelProperty(
            value = "额外的参数 auto_increment标记",
            required = true
    )
    private String extra;
    @ApiModelProperty(
            value = "字段描述，用于列表、表单、搜索前的显示",
            required = true
    )
    private String columnTitle;
    @ApiModelProperty(
            value = "是否在列表显示 0:不显示 1:显示",
            required = true
    )
    private Integer columnShow;
    @ApiModelProperty(
            value = "是否在搜索显示 0:不显示 1:显示",
            required = true
    )
    private Integer searchShow;
    @ApiModelProperty(
            value = "是否在表单显示 0:不显示 1:显示",
            required = true
    )
    private Integer formShow;
    @ApiModelProperty(
            value = "组件类型，文本框、日期等，取值参考字典[component_type]",
            required = true
    )
    private String componentType;
    @ApiModelProperty(
            value = "操作方式，等于、大于、小于、IN等，取值参考字典[query_type]",
            required = true
    )
    private String queryType;
    @ApiModelProperty(
            value = "字段校验，日期、时间、正则等，数组，有些包含类型和值两部分，例[require, min:1, max:99]，取值参考字典[valid_type]",
            required = true
    )
    private String[] validTypes;

    public CodeColumnsDTO() {
    }

    public String getColumnName() {
        return this.columnName;
    }

    public String getIsNullable() {
        return this.isNullable;
    }

    public String getDataType() {
        return this.dataType;
    }

    public String getColumnComment() {
        return this.columnComment;
    }

    public String getColumnKey() {
        return this.columnKey;
    }

    public String getExtra() {
        return this.extra;
    }

    public String getColumnTitle() {
        return this.columnTitle;
    }

    public Integer getColumnShow() {
        return this.columnShow;
    }

    public Integer getSearchShow() {
        return this.searchShow;
    }

    public Integer getFormShow() {
        return this.formShow;
    }

    public String getComponentType() {
        return this.componentType;
    }

    public String getQueryType() {
        return this.queryType;
    }

    public String[] getValidTypes() {
        return this.validTypes;
    }

    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    public void setIsNullable(final String isNullable) {
        this.isNullable = isNullable;
    }

    public void setDataType(final String dataType) {
        this.dataType = dataType;
    }

    public void setColumnComment(final String columnComment) {
        this.columnComment = columnComment;
    }

    public void setColumnKey(final String columnKey) {
        this.columnKey = columnKey;
    }

    public void setExtra(final String extra) {
        this.extra = extra;
    }

    public void setColumnTitle(final String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public void setColumnShow(final Integer columnShow) {
        this.columnShow = columnShow;
    }

    public void setSearchShow(final Integer searchShow) {
        this.searchShow = searchShow;
    }

    public void setFormShow(final Integer formShow) {
        this.formShow = formShow;
    }

    public void setComponentType(final String componentType) {
        this.componentType = componentType;
    }

    public void setQueryType(final String queryType) {
        this.queryType = queryType;
    }

    public void setValidTypes(final String[] validTypes) {
        this.validTypes = validTypes;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CodeColumnsDTO)) {
            return false;
        } else {
            CodeColumnsDTO other = (CodeColumnsDTO) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label159:
                {
                    Object this$columnName = this.getColumnName();
                    Object other$columnName = other.getColumnName();
                    if (this$columnName == null) {
                        if (other$columnName == null) {
                            break label159;
                        }
                    } else if (this$columnName.equals(other$columnName)) {
                        break label159;
                    }

                    return false;
                }

                Object this$isNullable = this.getIsNullable();
                Object other$isNullable = other.getIsNullable();
                if (this$isNullable == null) {
                    if (other$isNullable != null) {
                        return false;
                    }
                } else if (!this$isNullable.equals(other$isNullable)) {
                    return false;
                }

                Object this$dataType = this.getDataType();
                Object other$dataType = other.getDataType();
                if (this$dataType == null) {
                    if (other$dataType != null) {
                        return false;
                    }
                } else if (!this$dataType.equals(other$dataType)) {
                    return false;
                }

                label138:
                {
                    Object this$columnComment = this.getColumnComment();
                    Object other$columnComment = other.getColumnComment();
                    if (this$columnComment == null) {
                        if (other$columnComment == null) {
                            break label138;
                        }
                    } else if (this$columnComment.equals(other$columnComment)) {
                        break label138;
                    }

                    return false;
                }

                label131:
                {
                    Object this$columnKey = this.getColumnKey();
                    Object other$columnKey = other.getColumnKey();
                    if (this$columnKey == null) {
                        if (other$columnKey == null) {
                            break label131;
                        }
                    } else if (this$columnKey.equals(other$columnKey)) {
                        break label131;
                    }

                    return false;
                }

                Object this$extra = this.getExtra();
                Object other$extra = other.getExtra();
                if (this$extra == null) {
                    if (other$extra != null) {
                        return false;
                    }
                } else if (!this$extra.equals(other$extra)) {
                    return false;
                }

                Object this$columnTitle = this.getColumnTitle();
                Object other$columnTitle = other.getColumnTitle();
                if (this$columnTitle == null) {
                    if (other$columnTitle != null) {
                        return false;
                    }
                } else if (!this$columnTitle.equals(other$columnTitle)) {
                    return false;
                }

                label110:
                {
                    Object this$columnShow = this.getColumnShow();
                    Object other$columnShow = other.getColumnShow();
                    if (this$columnShow == null) {
                        if (other$columnShow == null) {
                            break label110;
                        }
                    } else if (this$columnShow.equals(other$columnShow)) {
                        break label110;
                    }

                    return false;
                }

                label103:
                {
                    Object this$searchShow = this.getSearchShow();
                    Object other$searchShow = other.getSearchShow();
                    if (this$searchShow == null) {
                        if (other$searchShow == null) {
                            break label103;
                        }
                    } else if (this$searchShow.equals(other$searchShow)) {
                        break label103;
                    }

                    return false;
                }

                Object this$formShow = this.getFormShow();
                Object other$formShow = other.getFormShow();
                if (this$formShow == null) {
                    if (other$formShow != null) {
                        return false;
                    }
                } else if (!this$formShow.equals(other$formShow)) {
                    return false;
                }

                label89:
                {
                    Object this$componentType = this.getComponentType();
                    Object other$componentType = other.getComponentType();
                    if (this$componentType == null) {
                        if (other$componentType == null) {
                            break label89;
                        }
                    } else if (this$componentType.equals(other$componentType)) {
                        break label89;
                    }

                    return false;
                }

                Object this$queryType = this.getQueryType();
                Object other$queryType = other.getQueryType();
                if (this$queryType == null) {
                    if (other$queryType != null) {
                        return false;
                    }
                } else if (!this$queryType.equals(other$queryType)) {
                    return false;
                }

                if (!Arrays.deepEquals(this.getValidTypes(), other.getValidTypes())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CodeColumnsDTO;
    }

    public int hashCode() {
        int result = 1;
        Object $columnName = this.getColumnName();
        result = result * 59 + ($columnName == null ? 43 : $columnName.hashCode());
        Object $isNullable = this.getIsNullable();
        result = result * 59 + ($isNullable == null ? 43 : $isNullable.hashCode());
        Object $dataType = this.getDataType();
        result = result * 59 + ($dataType == null ? 43 : $dataType.hashCode());
        Object $columnComment = this.getColumnComment();
        result = result * 59 + ($columnComment == null ? 43 : $columnComment.hashCode());
        Object $columnKey = this.getColumnKey();
        result = result * 59 + ($columnKey == null ? 43 : $columnKey.hashCode());
        Object $extra = this.getExtra();
        result = result * 59 + ($extra == null ? 43 : $extra.hashCode());
        Object $columnTitle = this.getColumnTitle();
        result = result * 59 + ($columnTitle == null ? 43 : $columnTitle.hashCode());
        Object $columnShow = this.getColumnShow();
        result = result * 59 + ($columnShow == null ? 43 : $columnShow.hashCode());
        Object $searchShow = this.getSearchShow();
        result = result * 59 + ($searchShow == null ? 43 : $searchShow.hashCode());
        Object $formShow = this.getFormShow();
        result = result * 59 + ($formShow == null ? 43 : $formShow.hashCode());
        Object $componentType = this.getComponentType();
        result = result * 59 + ($componentType == null ? 43 : $componentType.hashCode());
        Object $queryType = this.getQueryType();
        result = result * 59 + ($queryType == null ? 43 : $queryType.hashCode());
        result = result * 59 + Arrays.deepHashCode(this.getValidTypes());
        return result;
    }

    public String toString() {
        return "CodeColumnsDTO(columnName=" + this.getColumnName() + ", isNullable=" + this.getIsNullable() + ", dataType=" + this.getDataType() + ", columnComment=" + this.getColumnComment() + ", columnKey=" + this.getColumnKey() + ", extra=" + this.getExtra() + ", columnTitle=" + this.getColumnTitle() + ", columnShow=" + this.getColumnShow() + ", searchShow=" + this.getSearchShow() + ", formShow=" + this.getFormShow() + ", componentType=" + this.getComponentType() + ", queryType=" + this.getQueryType() + ", validTypes=" + Arrays.deepToString(this.getValidTypes()) + ")";
    }
}

