package com.green.firstproject.vo.store;

import java.time.LocalTime;

import lombok.Data;

@Data
public class StoreAddForm {
    private String name;
    private String zipcode;
    private String address;
    private String detailAddress;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer deliveryPrice;
    private String phone;
}
