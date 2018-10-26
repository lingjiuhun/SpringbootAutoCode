package com.code.base.util.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 响应对象封装
 *
 * @param <T>
 */
public class RestResponse<T> {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;

    public RestResponse() {
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public RestResponse<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public RestResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public RestResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
