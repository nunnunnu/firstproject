package com.green.firstproject.vo.order;

import java.time.format.DateTimeFormatter;
import java.util.List;

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
     private Long orderSeq;
     private String memberName;
     private String orderDate;
     private String storeName;
     private String orderStatus;
     private String pay;
     private String couponName;
     private Integer discountPrice;
     private Integer totalPrice;
     private Boolean cancellable; //취소 가능 여부
     private String request;
     private String menuList; //주문 메뉴 요약
     private String address;

     public OrderVO(OrderInfoEntity order) {
          this.orderSeq = order.getOiSeq();
          this.memberName=order.getMember().getMiName();
          this.orderDate = order.getOiOrderTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
          this.storeName=order.getStore().getSiName();
          setStatus(order);
          this.pay=order.getPay().getPayMethod();
          this.totalPrice = 0;
          if(order.getCoupon()!=null){
               this.couponName=order.getCoupon().getCiName();
               this.discountPrice=order.getCoupon().getCiDiscount();
               this.totalPrice -= discountPrice;
          }
          this.request = order.getOiRequest();
          this.menuList="";
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
     public void setTotalPrice(List<CartDetail> carts){
          for(CartDetail cart : carts){
               totalPrice += cart.getPrice();
          }
          if(couponName!=null){
               totalPrice = (int) (totalPrice*(1-discountPrice));
          }
     }

     // public void addOrderDetail(List<OrderDetailVO> orderDetailVOs){
     //      orderDetail=orderDetailVOs;
     // }

     public void setOrderPrice(OrderDetailEntity orderDetail){
          Integer rSizeSidePrice=2700;
          Integer lSizeSidePrice=3200;
          Integer rSizeDrinkPrice = 2600;
          Integer lSizeDrinkPrice = 2800;

          if(!(menuList==null || menuList.equals(""))){
               menuList+="+";
          }
          if(orderDetail.getOdBiseq()!=null){ //메뉴 선택
               menuList += orderDetail.getOdBiseq().getMenuName();
               this.totalPrice += orderDetail.getOdBiseq().getMenuPrice();
               if(orderDetail.getOdBiseq().getBurger()!=null && orderDetail.getOdBiseq().getSide()!=null && orderDetail.getOdBiseq().getDrink()!=null){ //세트메뉴
                    if(orderDetail.getOdLsotSeq()!=null){
                         totalPrice += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
                    }
                    if(orderDetail.getOdLdotSeq()!=null){
                         totalPrice += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
                    }
               }else if(orderDetail.getOdEiSeq()!=null){ //이벤트 메뉴
                    if(orderDetail.getOdLdotSeq()!=null){
                         totalPrice += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
                    }
                    if(orderDetail.getOdLdot2Seq()!=null){
                         totalPrice += orderDetail.getOdLdot2Seq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
                    }
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

     public void addCheckIngredientPrice(){
          this.totalPrice+=400;
     }
     
}
