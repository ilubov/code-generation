package com.i.lubov.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class TablesVO implements Serializable {
    @ApiModelProperty("表名称")
    private String tableName;
    @ApiModelProperty("数据库引擎")
    private String engine;
    @ApiModelProperty("编码集")
    private String tableCollation;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建日期")
    private Date createTime;

    public TablesVO() {
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

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TablesVO)) {
            return false;
        } else {
            TablesVO other = (TablesVO) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71:
                {
                    Object this$tableName = this.getTableName();
                    Object other$tableName = other.getTableName();
                    if (this$tableName == null) {
                        if (other$tableName == null) {
                            break label71;
                        }
                    } else if (this$tableName.equals(other$tableName)) {
                        break label71;
                    }

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

                label57:
                {
                    Object this$tableCollation = this.getTableCollation();
                    Object other$tableCollation = other.getTableCollation();
                    if (this$tableCollation == null) {
                        if (other$tableCollation == null) {
                            break label57;
                        }
                    } else if (this$tableCollation.equals(other$tableCollation)) {
                        break label57;
                    }

                    return false;
                }

                Object this$remark = this.getRemark();
                Object other$remark = other.getRemark();
                if (this$remark == null) {
                    if (other$remark != null) {
                        return false;
                    }
                } else if (!this$remark.equals(other$remark)) {
                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime == null) {
                        return true;
                    }
                } else if (this$createTime.equals(other$createTime)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TablesVO;
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
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        return result;
    }

    public String toString() {
        return "TablesVO(tableName=" + this.getTableName() + ", engine=" + this.getEngine() + ", tableCollation=" + this.getTableCollation() + ", remark=" + this.getRemark() + ", createTime=" + this.getCreateTime() + ")";
    }
}
