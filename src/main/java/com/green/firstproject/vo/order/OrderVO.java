package com.green.firstproject.vo.order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.master.CouponInfoEntity;
import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
     private Long seq;
     private MemberInfoEntity member;
     private LocalDateTime orderDate;
     private StoreInfoEntity store;
     private String status;
     private PaymentInfoEntity pay;
     private CouponInfoEntity coupon;
     private Integer totalPrice;
     private List<OrderDetailVO> orderDetail;

     public OrderVO(OrderInfoEntity order) {
          this.seq = order.getOiSeq();
          this.member=order.getMember();
          this.orderDate = order.getOiOrderTime();
          this.store=order.getStore();
          setStatus(order);
          this.pay=order.getPay();
          this.coupon=order.getCoupon();
          this.totalPrice = 0;
     }

     public void setStatus(OrderInfoEntity order){
          if(order.getOiStatus()==1){
               this.status="접수";
          }else if(order.getOiStatus()==2){
               this.status = "준비중";
          }else if(order.getOiStatus()==3){
               this.status = "배송중";
          }else if(order.getOiStatus()==4){
               this.status = "배송완료";
          }else if(order.getOiStatus()==5){
               this.status = "주문취소";
          }
     }
     public void setTotalPrice(List<CartDetail> carts){
          for(CartDetail cart : carts){
               totalPrice += cart.getPrice();
          }
          if(coupon!=null){
               totalPrice = (int) (totalPrice-coupon.getCiDiscount());
          }
     }

     public void addOrderDetail(List<OrderDetailVO> orderDetailVOs){
          for(OrderDetailVO vo : orderDetailVOs){
               this.orderDetail.add(vo);
          }
     }
     
}
