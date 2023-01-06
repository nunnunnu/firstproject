package com.green.firstproject.entity.order.cart;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {
     private Long seq;
     private Integer odCount;
     // private OrderInfoEntity order;
     private MenuInfoEntity menu;
     private EventInfoEntity event;
     private SideOptionEntity side;
     private DrinkOptionEntity drink;
     private DrinkOptionEntity drink2;
     private List<IngredientsInfoEntity> ingredient;

     public CartDetail(Long seq, Integer count, MenuInfoEntity menu){
          this.seq = seq;
          this.odCount=count;
          this.menu=menu;
          this.ingredient = new ArrayList<>();
     }
     public CartDetail(Long seq, Integer count, EventInfoEntity event){
          this.seq = seq;
          this.odCount=count;
          this.event=event;
          this.ingredient = new ArrayList<>();
     }

     public void addIngredient(IngredientsInfoEntity ingrEntities){
          this.ingredient.add(ingrEntities);
     }



}
