package com.green.firstproject.vo.order.cart;

import java.util.Set;

import lombok.Data;

@Data
public class UpdateCartVO {
     private Integer cnt;
     private Long side;
     private Long drink;
     private Long drink2;
     private Set<Long> ingredients;
}
