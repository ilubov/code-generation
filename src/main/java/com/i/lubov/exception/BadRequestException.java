package com.i.lubov.exception;

public class BadRequestException extends RuntimeException {
    private Integer code;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
}
