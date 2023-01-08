package com.green.firstproject.vo.order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.master.CouponInfoEntity;
import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.entity.order.cart.CartDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
     private Long seq;
     private String member;
     private LocalDateTime orderDate;
     private String store;
     private String status;
     private PaymentInfoEntity pay;
     private String coupon;
     private Double discountPrice;
     private Integer totalPrice;
     private List<OrderDetailVO> orderDetail;

     public OrderVO(OrderInfoEntity order) {
          this.seq = order.getOiSeq();
          this.member=order.getMember().getMiName();
          this.orderDate = order.getOiOrderTime();
          this.store=order.getStore().getSiName();
          setStatus(order);
          this.pay=order.getPay();
          if(order.getCoupon()!=null){
               this.coupon=order.getCoupon().getCiName();
               this.discountPrice=order.getCoupon().getCiDiscount();
          }
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
               totalPrice = (int) (totalPrice*(1-discountPrice));
          }
     }

     public void addOrderDetail(List<OrderDetailVO> orderDetailVOs){
          orderDetail=orderDetailVOs;
     }

     public void setOrderPrice(OrderDetailEntity orderDetail){
          Integer rSizeSidePrice=2700;
          Integer lSizeSidePrice=3200;
          Integer rSizeDrinkPrice = 2600;
          Integer lSizeDrinkPrice = 2800;
          if(orderDetail.getOdBiseq()!=null){
               this.totalPrice += orderDetail.getOdBiseq().getMenuPrice();
          }else if(orderDetail.getOdEiSeq()!=null){
               this.totalPrice += orderDetail.getOdEiSeq().getEiPrice();
          }
          if(orderDetail.getOdBiseq().getBurger()!=null && orderDetail.getOdBiseq().getSide()!=null && orderDetail.getOdBiseq().getDrink()!=null){
               if(orderDetail.getOdLsotSeq()!=null){
                    totalPrice += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    totalPrice += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }else if(orderDetail.getOdEiSeq() !=null){
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
          
          // int count = 0;
          // if(orderDetail.getOdBiseq().getMenuSelect()){
          //      for(IngredientsInfoEntity i : orderDetail.get){
          //           if(i.getIiPrice()==0){
          //                if(count>1){
          //                     totalPrice+=400;
          //                }
          //                count++;
          //           }else{
          //                totalPrice+= i.getIiPrice();
          //           }
          //      }
          // }

     }

     public void addIngredientPrice(OrderIngredientsDetailEntity ing){
          this.totalPrice += ing.getIngredient().getIiPrice();
     }
     
}
