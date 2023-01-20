package com.green.firstproject.vo.order;

import java.util.Set;

import com.green.firstproject.entity.order.OrderDetailEntity;

import lombok.Data;

@Data
public class MyOrderDetailVO {
     private String orderMenuName;
     private String eventMenuName;
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
          if(od.getOdEiSeq()!=null){
               this.orderMenuName=od.getOdEiSeq().getEiName();
               this.menuEx=od.getOdEiSeq().getEiDetail();
          }
          if(od.getOdLsotSeq()!=null){
               this.composition+=od.getOdLsotSeq().getSoName()+"교환";
          }
          if(od.getOdLdotSeq()!=null){
               if(composition!=null || composition.equals("")){
                    this.composition+=", ";
               }
               this.composition+=od.getOdLdotSeq().getDoName()+"교환";
          }
          if(od.getOdLdot2Seq()!=null){
               if(composition!=null || composition.equals("")){
                    this.composition+=", ";
               }
               this.composition+=od.getOdLdot2Seq().getDoName()+"교환";
          }
          if(composition==""){
               composition=null;
          }
     }
     public void addOrderIngredients(Set<OrderIngredientsVO> ingredientsVOs){
          int count=0;
          for(OrderIngredientsVO vo : ingredientsVOs){
               this.price+=vo.getIngredient().getIngredientPrice();
               if(vo.getIngredient().getIngredientPrice()==0){
                    count++;
               }
          }
          if(count>1){
               this.price+=400;
          }
     }
     public void addPrice(OrderDetailEntity orderDetail){
          Integer rSizeSidePrice=2700;
          Integer lSizeSidePrice=3200;
          Integer rSizeDrinkPrice = 2600;
          Integer lSizeDrinkPrice = 2800;
          if(orderDetail.getOdBiseq()!=null){
               this.price += orderDetail.getOdBiseq().getMenuPrice();
          }else if(orderDetail.getOdEiSeq()!=null){
               this.price += orderDetail.getOdEiSeq().getEiPrice();
          }
          if(orderDetail.getOdBiseq().getBurger()!=null && orderDetail.getOdBiseq().getSide()!=null && orderDetail.getOdBiseq().getDrink()!=null){
               if(orderDetail.getOdLsotSeq()!=null){
                    price += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }else if(orderDetail.getOdEiSeq() !=null){
               if(orderDetail.getOdLsotSeq()!=null){
                    price += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
               if(orderDetail.getOdLdot2Seq()!=null){
                    price += orderDetail.getOdLdot2Seq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }
     }
}
