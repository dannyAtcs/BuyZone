package com.atcs.product.BuyZone.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {
    private  String message;
    private  HttpStatus httpStatus;
    private Date timestamp;



}








