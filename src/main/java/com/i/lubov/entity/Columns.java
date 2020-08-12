package com.i.lubov.entity;

import java.io.Serializable;

public class Columns implements Serializable {
    private String columnName;
    private String isNullable;
    private String dataType;
    private String columnComment;
    private String columnKey;
    private String extra;

    public Columns() {
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
        } else if (!(o instanceof Columns)) {
            return false;
        } else {
            Columns other = (Columns) o;
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

                label62:
                {
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

                label55:
                {
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
        return other instanceof Columns;
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
        return "Columns(columnName=" + this.getColumnName() + ", isNullable=" + this.getIsNullable() + ", dataType=" + this.getDataType() + ", columnComment=" + this.getColumnComment() + ", columnKey=" + this.getColumnKey() + ", extra=" + this.getExtra() + ")";
    }
}
