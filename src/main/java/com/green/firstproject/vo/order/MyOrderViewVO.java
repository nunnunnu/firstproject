package com.green.firstproject.vo.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.vo.master.PaymentInfoVO;

import lombok.Data;
@Data
public class MyOrderViewVO {
     private String member;
     private LocalDateTime orderDate;
     private String store;
     private String status;
     private PaymentInfoVO pay;
     private String coupon;
     private Integer discountPrice;
     private Integer totalPrice;
     private List<MyOrderDetailVO> orderDetail;
     private Boolean cancellable;
     private String request;

     public MyOrderViewVO(OrderInfoEntity order){
          orderDetail = new ArrayList<>();
          this.member = order.getMember().getMiName();
          this.orderDate = order.getOiOrderTime();
          this.store = order.getStore().getSiName();
          this.pay = new PaymentInfoVO(order.getPay());
          setStatus(order);
          if(order.getCoupon()!=null){
               this.coupon=order.getCoupon().getCiName();
               this.discountPrice=order.getCoupon().getCiDiscount();
          }
          this.request = order.getOiRequest();
          this.totalPrice = 0;
     }

     public void setStatus(OrderInfoEntity order){
          if(order.getOiStatus()==1){
               this.status="접수";
               this.cancellable=true;
          }else if(order.getOiStatus()==2){
               this.status = "준비중";
               this.cancellable=true;
          }else if(order.getOiStatus()==3){
               this.status = "배송중";
               this.cancellable=false;
          }else if(order.getOiStatus()==4){
               this.status = "배송완료";
               this.cancellable=false;
          }else if(order.getOiStatus()==5){
               this.status = "주문취소";
               this.cancellable=false;
          }
     }

     public void setOrderPrice(OrderDetailEntity orderDetail){
          Integer rSizeSidePrice=2700;
          Integer lSizeSidePrice=3200;
          Integer rSizeDrinkPrice = 2600;
          Integer lSizeDrinkPrice = 2800;

          if(orderDetail.getOdBiseq()!=null){ //메뉴 선택
               this.totalPrice += orderDetail.getOdBiseq().getMenuPrice();
               if(orderDetail.getOdBiseq().getBurger()!=null && orderDetail.getOdBiseq().getSide()!=null && orderDetail.getOdBiseq().getDrink()!=null){ //세트메뉴
                    if(orderDetail.getOdLsotSeq()!=null){
                         totalPrice += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
                    }
                    if(orderDetail.getOdLdotSeq()!=null){
                         totalPrice += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
                    }
               }
          }else if(orderDetail.getOdEiSeq()!=null){ //이벤트 선택
               this.totalPrice += orderDetail.getOdEiSeq().getEiPrice();
               if(orderDetail.getOdLsotSeq()!=null){
                    totalPrice += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    totalPrice += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
               if(orderDetail.getOdLdot2Seq()!=null){
                    totalPrice += orderDetail.getOdLdot2Seq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }
     }

     public void totalPrice(){
          for(MyOrderDetailVO o : orderDetail){
               this.totalPrice+=o.getPrice();
          }
     }

     public void addOrderDetail(MyOrderDetailVO orderDetail){
          this.orderDetail.add(orderDetail);
     }
     
}
