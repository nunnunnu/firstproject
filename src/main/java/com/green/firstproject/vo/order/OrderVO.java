package com.green.firstproject.vo.order;

import java.time.LocalDateTime;
import java.util.List;

import com.green.firstproject.entity.master.CouponInfoEntity;
import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;

public class OrderVO {
     private Long oiSeq;
     private MemberInfoEntity member;
     private LocalDateTime oiOrderTime;
     private StoreInfoEntity store;
     private Integer oiStatus;
     private PaymentInfoEntity pay;
     private CouponInfoEntity coupon;
     private Integer totalPrice;
     private List<OrderDetailVO> orderDetail;
}
