package com.atcs.product.BuyZone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pincode {


    private String pin;
    private Integer days;
}
