package com.green.firstproject.entity.order.cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     private Set<IngredientsInfoEntity> ingredient; //중복 제거를 위해 set으로 변경함

     public CartDetail(Long seq, Integer count, MenuInfoEntity menu){
          this.seq = seq;
          this.odCount=count;
          this.menu=menu;
          this.ingredient = new HashSet<>();
     }
     public CartDetail(Long seq, Integer count, EventInfoEntity event){
          this.seq = seq;
          this.odCount=count;
          this.event=event;
          this.ingredient = new HashSet<>();
     }

     public void addIngredient(IngredientsInfoEntity ingrEntities){
          this.ingredient.add(ingrEntities);
     }
     public void changeIngredient(Set<IngredientsInfoEntity> ing){
          ingredient = ing;
     }
}
