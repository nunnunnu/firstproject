package com.green.firstproject.vo.order.cart;

import java.util.Set;

import lombok.Data;

@Data
public class AddCart {
     private Long menu;
     private Long event;
     private Long side;
     private Long drink;
     private Long drink2;
     private Set<Long> ingredients;
     private Integer count;
}
