package com.green.firstproject.vo.order;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.vo.master.PaymentInfoVO;

import lombok.Data;
@Data
public class MyOrderViewVO {
     private String memberName;
     private String orderDate;
     private String storeName;
     private String orderStatus;
     private PaymentInfoVO pay;
     private String couponName;
     private Integer discountPrice;
     private Integer totalPrice;
     private List<MyOrderDetailVO> orderDetail;
     private Boolean cancellable;
     private String request;
     private String address;

     public MyOrderViewVO(OrderInfoEntity order){
          orderDetail = new ArrayList<>();
          this.memberName = order.getMember().getMiName();
          this.orderDate = order.getOiOrderTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
          this.storeName = order.getStore().getSiName();
          this.pay = new PaymentInfoVO(order.getPay());
          setStatus(order);
          if(order.getCoupon()!=null){
               this.couponName=order.getCoupon().getCoupon().getCiName();
               this.discountPrice=order.getCoupon().getCoupon().getCiDiscount();
          }
          this.request = order.getOiRequest();
          this.totalPrice = 0;
          this.address=order.getOiAddress();
     }

     public void setStatus(OrderInfoEntity order){
          if(order.getOiStatus()==1){
               this.orderStatus="접수";
               this.cancellable=true;
          }else if(order.getOiStatus()==2){
               this.orderStatus = "준비중";
               this.cancellable=true;
          }else if(order.getOiStatus()==3){
               this.orderStatus = "배송중";
               this.cancellable=false;
          }else if(order.getOiStatus()==4){
               this.orderStatus = "배송완료";
               this.cancellable=false;
          }else if(order.getOiStatus()==5){
               this.orderStatus = "주문취소";
               this.cancellable=false;
          }
     }

     public void setOrderPrice(OrderDetailEntity orderDetail){
          
          MenuInfoEntity menu = orderDetail.getOdBiseq();

          if(menu!=null){ //메뉴 선택
               this.totalPrice += menu.getMenuPrice();
               if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){ //세트메뉴
                    if(orderDetail.getOdLsotSeq()!=null){
                         totalPrice += orderDetail.getOdLsotSeq().getSoPrice();
                    }
                    if(orderDetail.getOdLdotSeq()!=null){
                         totalPrice += orderDetail.getOdLdotSeq().getDoPrice();
                    }
               }else if(menu.getEvent()!=null){
                    if(orderDetail.getOdLdotSeq()!=null){
                         totalPrice += orderDetail.getOdLdotSeq().getDoPrice();
                    }
                    if(orderDetail.getOdLdot2Seq()!=null){
                         totalPrice += orderDetail.getOdLdotSeq().getDoPrice();
                    }
               }
          }
     }

     public void totalPrice(){
          for(MyOrderDetailVO o : orderDetail){
               this.totalPrice+=o.getPrice();
          }
          if(discountPrice!=null && discountPrice!=0){
               this.totalPrice-=discountPrice;
          }
     }

     public void addOrderDetail(MyOrderDetailVO orderDetail){
          this.orderDetail.add(orderDetail);
     }
     
}
