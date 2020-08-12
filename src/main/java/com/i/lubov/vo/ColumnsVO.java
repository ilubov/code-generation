package com.i.lubov.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class ColumnsVO implements Serializable {
    @ApiModelProperty("数据库字段名称")
    private String columnName;
    @ApiModelProperty("允许空值 YES NO")
    private String isNullable;
    @ApiModelProperty("字段类型bigint、varchar、bit等")
    private String dataType;
    @ApiModelProperty("数据库字段注释")
    private String columnComment;
    @ApiModelProperty("主键、外键标记PRI、UNI、空")
    private String columnKey;
    @ApiModelProperty("额外的参数 auto_increment标记")
    private String extra;

    public ColumnsVO() {
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

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ColumnsVO)) {
            return false;
        } else {
            ColumnsVO other = (ColumnsVO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$columnName = this.getColumnName();
                Object other$columnName = other.getColumnName();
                if (this$columnName == null) {
                    if (other$columnName != null) {
                        return false;
                    }
                } else if (!this$columnName.equals(other$columnName)) {
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

                label62: {
                    Object this$columnComment = this.getColumnComment();
                    Object other$columnComment = other.getColumnComment();
                    if (this$columnComment == null) {
                        if (other$columnComment == null) {
                            break label62;
                        }
                    } else if (this$columnComment.equals(other$columnComment)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$columnKey = this.getColumnKey();
                    Object other$columnKey = other.getColumnKey();
                    if (this$columnKey == null) {
                        if (other$columnKey == null) {
                            break label55;
                        }
                    } else if (this$columnKey.equals(other$columnKey)) {
                        break label55;
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

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ColumnsVO;
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
        return result;
    }

    public String toString() {
        return "ColumnsVO(columnName=" + this.getColumnName() + ", isNullable=" + this.getIsNullable() + ", dataType=" + this.getDataType() + ", columnComment=" + this.getColumnComment() + ", columnKey=" + this.getColumnKey() + ", extra=" + this.getExtra() + ")";
    }
}
