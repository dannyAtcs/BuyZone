package com.atcs.product.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e){
        // 1.create payload containing exception in details
        ApiException apiException = new ApiException(e.getMessage(), e.getHttpStatus(), ZonedDateTime.now(ZoneId.of("Z")));
        // 2.return response entity
        return new ResponseEntity<ApiException>(apiException,e.getHttpStatus());

    }
}
