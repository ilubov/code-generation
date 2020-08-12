package com.i.lubov.entity;

import java.io.Serializable;
import java.util.Date;

public class Tables implements Serializable {
    private String tableName;
    private String engine;
    private String tableCollation;
    private String remark;
    private String tableComment;
    private Date createTime;

    public Tables() {
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getEngine() {
        return this.engine;
    }

    public String getTableCollation() {
        return this.tableCollation;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getTableComment() {
        return this.tableComment;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public void setEngine(final String engine) {
        this.engine = engine;
    }

    public void setTableCollation(final String tableCollation) {
        this.tableCollation = tableCollation;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public void setTableComment(final String tableComment) {
        this.tableComment = tableComment;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Tables)) {
            return false;
        } else {
            Tables other = (Tables) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$tableName = this.getTableName();
                Object other$tableName = other.getTableName();
                if (this$tableName == null) {
                    if (other$tableName != null) {
                        return false;
                    }
                } else if (!this$tableName.equals(other$tableName)) {
                    return false;
                }

                Object this$engine = this.getEngine();
                Object other$engine = other.getEngine();
                if (this$engine == null) {
                    if (other$engine != null) {
                        return false;
                    }
                } else if (!this$engine.equals(other$engine)) {
                    return false;
                }

                Object this$tableCollation = this.getTableCollation();
                Object other$tableCollation = other.getTableCollation();
                if (this$tableCollation == null) {
                    if (other$tableCollation != null) {
                        return false;
                    }
                } else if (!this$tableCollation.equals(other$tableCollation)) {
                    return false;
                }

                label62:
                {
                    Object this$remark = this.getRemark();
                    Object other$remark = other.getRemark();
                    if (this$remark == null) {
                        if (other$remark == null) {
                            break label62;
                        }
                    } else if (this$remark.equals(other$remark)) {
                        break label62;
                    }

                    return false;
                }

                label55:
                {
                    Object this$tableComment = this.getTableComment();
                    Object other$tableComment = other.getTableComment();
                    if (this$tableComment == null) {
                        if (other$tableComment == null) {
                            break label55;
                        }
                    } else if (this$tableComment.equals(other$tableComment)) {
                        break label55;
                    }

                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Tables;
    }

    public int hashCode() {
        int result = 1;
        Object $tableName = this.getTableName();
        result = result * 59 + ($tableName == null ? 43 : $tableName.hashCode());
        Object $engine = this.getEngine();
        result = result * 59 + ($engine == null ? 43 : $engine.hashCode());
        Object $tableCollation = this.getTableCollation();
        result = result * 59 + ($tableCollation == null ? 43 : $tableCollation.hashCode());
        Object $remark = this.getRemark();
        result = result * 59 + ($remark == null ? 43 : $remark.hashCode());
        Object $tableComment = this.getTableComment();
        result = result * 59 + ($tableComment == null ? 43 : $tableComment.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        return result;
    }

    public String toString() {
        return "Tables(tableName=" + this.getTableName() + ", engine=" + this.getEngine() + ", tableCollation=" + this.getTableCollation() + ", remark=" + this.getRemark() + ", tableComment=" + this.getTableComment() + ", createTime=" + this.getCreateTime() + ")";
    }
}
