package com.green.firstproject.vo.order;

import java.util.Set;

import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;

import lombok.Data;

@Data
public class MyOrderDetailVO {
     private String orderMenuName;
     private Integer price;
     private String menuEx;
     private Integer count;
     private String composition;
     
     public MyOrderDetailVO(OrderDetailEntity od) {
          this.price=0;
          this.count=od.getOdCount();
          this.composition="";
          if(od.getOdBiseq()!=null){
               this.orderMenuName=od.getOdBiseq().getMenuName();
               this.menuEx=od.getOdBiseq().getMenuEx();
          }
          if(od.getOdLsotSeq()!=null){
               this.composition+=od.getOdLsotSeq().getSoName();
          }
          if(od.getOdLdotSeq()!=null){
               if(composition!=null && !composition.equals("")){
                    this.composition+=", ";
               }
               this.composition+=od.getOdLdotSeq().getDoName();
          }
          if(od.getOdLdot2Seq()!=null){
               if(composition!=null && !composition.equals("")){
                    this.composition+=", ";
               }
               this.composition+=od.getOdLdot2Seq().getDoName();
          }
     }
     public void addOrderIngredients(Set<OrderIngredientsVO> ingredientsVOs, int num){
          int count=0;
          int temp = 0;
          for(OrderIngredientsVO vo : ingredientsVOs){
               temp+=vo.getIngredient().getIngredientPrice();
               if(vo.getIngredient().getIngredientPrice()==0){
                    count++;
               }
          }
          if(count>1){
               temp+=400;
          }
          this.price += temp * num;
     }
     public void addPrice(OrderDetailEntity orderDetail){
          MenuInfoEntity menu = orderDetail.getOdBiseq();
          this.price += menu.getMenuPrice();
          
          if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
               if(orderDetail.getOdLsotSeq()!=null){
                    price += orderDetail.getOdLsotSeq().getSoPrice();
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice();
               }
          }else if(menu.getEvent() !=null){
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice();
               }
               if(orderDetail.getOdLdot2Seq()!=null){
                    price += orderDetail.getOdLdot2Seq().getDoPrice();
               }
          }
          this.price = price * orderDetail.getOdCount();
     }
     public void ingredientName(OrderIngredientsDetailEntity i) {
          if(composition!=null && !composition.equals("")){
                    this.composition+=", ";
          }
          this.composition+=i.getIngredient().getIiName()+" 추가";
          if(composition==""){
               composition=null;
          }
     }
}
