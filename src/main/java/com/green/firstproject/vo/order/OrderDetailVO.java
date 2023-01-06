package com.green.firstproject.vo.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVO {
     private Long Seq ;
     @JsonIgnore
     private OrderInfoEntity order;
     private MenuInfoEntity menu;
     private EventInfoEntity event;
     private Integer count;
     private SideOptionEntity sideOpt;
     private DrinkOptionEntity drinkOpt;
     private DrinkOptionEntity drinkopt2;
     private List<OrderIngredientsVO> ingredients = new ArrayList<>();
     private int price;

     public OrderDetailVO(OrderDetailEntity orderDetail){
          this.Seq = orderDetail.getOdSeq();
          this.order = orderDetail.getOdOiseq();
          this.menu = orderDetail.getOdBiseq();
          this.count=orderDetail.getOdCount();
          this.sideOpt=orderDetail.getOdLsotSeq();
          this.drinkOpt=orderDetail.getOdLdotSeq();
          this.drinkopt2=orderDetail.getOdLdot2Seq();
     }

     public void addOrderIngredients(List<OrderIngredientsVO> ingredientsVOs){
          for(OrderIngredientsVO vo : ingredientsVOs){
               this.ingredients.add(vo);
          }
     }

     public void setDetailPrice(CartDetail cart){
          this.price = cart.getPrice();
     }

}
