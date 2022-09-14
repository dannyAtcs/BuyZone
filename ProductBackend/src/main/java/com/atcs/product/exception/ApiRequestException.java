package com.atcs.product.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{
    private static final long serialVersionUID = 3432079422625118651L;
    private HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
    public ApiRequestException(String message) {
        super(message);
    }
    public ApiRequestException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
