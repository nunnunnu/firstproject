package com.green.firstproject.vo.order;

import java.util.List;

import com.green.firstproject.entity.order.cart.CartDetail;

import lombok.Data;
@Data
public class OrderFormVO {
     private Long pay;
     private List<CartDetail> cart;
     private String message;
     private Long couponSeq;
}
