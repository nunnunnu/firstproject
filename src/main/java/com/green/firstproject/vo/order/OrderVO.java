package com.green.firstproject.vo.order;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
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
               this.couponName=order.getCoupon().getCoupon().getCiName();
               this.discountPrice=order.getCoupon().getCoupon().getCiDiscount();
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
          if(!(menuList==null || menuList.equals(""))){
               menuList+="+";
          }
          MenuInfoEntity menu = orderDetail.getOdBiseq();
          int price = 0;
          if(menu!=null){ //메뉴 선택
               menuList += menu.getMenuName();
               price += menu.getMenuPrice();
               if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){ //세트메뉴
                    if(orderDetail.getOdLsotSeq()!=null){
                         price += orderDetail.getOdLsotSeq().getSoPrice();
                    }
                    if(orderDetail.getOdLdotSeq()!=null){
                         price += orderDetail.getOdLdotSeq().getDoPrice();
                    }
               }else if(menu.getEvent()!=null){ //이벤트 메뉴
                    if(orderDetail.getOdLdotSeq()!=null){
                         price += orderDetail.getOdLdotSeq().getDoPrice();
                    }
                    if(orderDetail.getOdLdot2Seq()!=null){
                         price += orderDetail.getOdLdot2Seq().getDoPrice();
                    }
               }
          }
          
          this.totalPrice += price * orderDetail.getOdCount();
     }

     public void addIngredientPrice(OrderIngredientsDetailEntity ing, int count){
          this.totalPrice += ing.getIngredient().getIiPrice() * count;
     }

     public void addCheckIngredientPrice(int count){
          this.totalPrice+=400 * count;
     }
     
}
