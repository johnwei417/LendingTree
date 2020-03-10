package com.honglin.common;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private Object result;

    public ApiResponse() {

    }

    public ApiResponse(HttpStatus status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
