package com.i.lubov.comps;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BatchResult implements Serializable {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("返回值 0成功 -1异常 其它数字是自定义异常")
    private int code;
    @ApiModelProperty("描述")
    private String msg;

    public BatchResult() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "BatchResult{id=" + this.id + ", code=" + this.code + ", msg='" + this.msg + '\'' + '}';
    }
}

