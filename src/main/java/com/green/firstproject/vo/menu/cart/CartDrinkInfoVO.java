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
               this.drinkPrice=0;
               if(menuSize == 1){
                    int price = drink.getDoPrice()-2600;
                    if(price<0){
                         this.drinkPrice = 0;
     
                    }else{
                         this.drinkPrice = price;
                    }
               }else if(menuSize==2){
                    int price = drink.getDoPrice()-2800;
                    if(price<0){
                         this.drinkPrice = 0;
     
                    }else{
                         this.drinkPrice += price;
                    }
               }
          }
     }
     public CartDrinkInfoVO(DrinkOptionEntity drink){
          this.drinkSeq = drink.getDoSeq();
          this.drinkName = drink.getDoName();
          int price = drink.getDoPrice()-2600;
          if(price<0){
               this.drinkPrice = 0;
          }else{
               this.drinkPrice = price;
          }
     }
}
