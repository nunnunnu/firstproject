package com.green.firstproject.vo.menu.cart;

import com.green.firstproject.entity.menu.option.DrinkOptionEntity;

import lombok.Data;

@Data
public class CartDrinkInfoVO {
     private Long   drinkSeq;
     private String drinkName;
     private Integer drinkPrice;

     public CartDrinkInfoVO(DrinkOptionEntity drink, Integer menuSize){
          if(drink!=null){
               this.drinkSeq = drink.getDoSeq();
               this.drinkName = drink.getDoName();
               this.drinkPrice = drink.getDoPrice();
          }
     }
     public CartDrinkInfoVO(DrinkOptionEntity drink){
          this.drinkSeq = drink.getDoSeq();
          this.drinkName = drink.getDoName();
          int price = drink.getDoPrice();
          this.drinkPrice = price;
     }
}
