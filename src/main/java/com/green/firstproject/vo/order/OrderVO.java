package com.green.firstproject.vo.order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.master.CouponInfoEntity;
import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
     @JsonIgnore
     private final Integer R_SIZE_SIDE_PRICE=2700;
     @JsonIgnore
     private final Integer L_SIZE_SIDE_PRICE=3200;
     @JsonIgnore
     private final Integer R_SIZE_DRINK_PRICE = 2600;
     @JsonIgnore
     private final Integer L_SIZE_DRINK_PRICE = 2800;
     
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
     public void setTotalPrice(OrderDetailVO orderVo){
          if(orderVo.getMenu()!=null){
               this.totalPrice+=(orderVo.getMenu().getMenuPrice()* orderVo.getCount());
               if(orderVo.getMenu().getBurger()!=null && orderVo.getMenu().getDrink()!=null && orderVo.getMenu().getSide()!=null){
                    if(orderVo.getSideOpt()!=null){
                         totalPrice+=orderVo.getSideOpt().getSoPrice() - (orderVo.getMenu().getMenuSize()==1?R_SIZE_SIDE_PRICE:L_SIZE_SIDE_PRICE);
                    }else if(orderVo.getDrinkOpt()!=null){
                    totalPrice+=orderVo.getDrinkOpt().getDoPrice() - (orderVo.getMenu().getMenuSize()==1?R_SIZE_DRINK_PRICE:L_SIZE_DRINK_PRICE);
                    }
               }
          }else if(orderVo.getEvent()!=null){
               this.totalPrice+=(orderVo.getEvent().getEiPrice()* orderVo.getCount());
               if(orderVo.getMenu().getBurger()!=null && orderVo.getMenu().getDrink()!=null && orderVo.getMenu().getSide()!=null){
                    if(orderVo.getSideOpt()!=null){
                         totalPrice+=orderVo.getSideOpt().getSoPrice() - (orderVo.getMenu().getMenuSize()==1?R_SIZE_SIDE_PRICE:L_SIZE_SIDE_PRICE);
                    }
                    if(orderVo.getDrinkOpt()!=null){
                         totalPrice+=orderVo.getDrinkOpt().getDoPrice() - (orderVo.getMenu().getMenuSize()==1?R_SIZE_DRINK_PRICE:L_SIZE_DRINK_PRICE);
                    }
                    if(orderVo.getDrinkopt2()!=null){
                    totalPrice+=orderVo.getDrinkopt2().getDoPrice() - (orderVo.getMenu().getMenuSize()==1?R_SIZE_DRINK_PRICE:L_SIZE_DRINK_PRICE);
                    }
               }
          }
          int count=0;
          for(OrderIngredientsVO i : orderVo.getIngredients()){
               if(i.getIngredient().getIiPrice()==0){
                    count++;
                    this.totalPrice+=i.getIngredient().getIiPrice();
               }
          }
          if(count>1){
               this.totalPrice+=(count-1)*400;
          }
          
     }

     public void addOrderDetail(List<OrderDetailVO> orderDetailVOs){
          for(OrderDetailVO vo : orderDetailVOs){
               this.orderDetail.add(vo);
          }
     }
     
}
