package com.i.lubov.comps;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Pager<T> implements Serializable {
    @ApiModelProperty(value = "页号", required = true)
    @Min(1L)
    @NotNull(
            message = "面号值必须大于0"
    )
    private int page;
    @ApiModelProperty(value = "分页大小", required = true)
    @Min(1L)
    @Max(100L)
    @NotNull(message = "分页取值在1-100之间")
    private int size;
    @ApiModelProperty("记录总数")
    private int count;

    public Pager() {
    }

    public <E> IPage<E> build(Class<E> clazz) {
        IPage<E> page = new Page((long) this.page, (long) this.size);
        return page;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString() {
        return "Pager{page=" + this.page + ", size=" + this.size + ", count=" + this.count + '}';
    }
}
