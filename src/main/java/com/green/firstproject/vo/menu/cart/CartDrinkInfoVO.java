package com.green.firstproject.vo.menu.cart;

import com.green.firstproject.entity.menu.option.DrinkOptionEntity;

import lombok.Data;

@Data
public class CartDrinkInfoVO {
     private Long   drinkSeq;
     private String drinkName;
     private String drinkPrice;

     public CartDrinkInfoVO(DrinkOptionEntity drink, Integer menuSize){
          this.drinkSeq = drink.getDoSeq();
          this.drinkName = drink.getDoName();
          this.drinkPrice="+";
          if(menuSize == 1){
               int price = drink.getDoPrice()-2600;
               if(price<0){
                    this.drinkPrice += "0원";

               }else{
                    this.drinkPrice += price+"원";
               }
          }else if(menuSize==2){
                              int price = drink.getDoPrice()-2800;
               if(price<0){
                    this.drinkPrice += "0원";

               }else{
                    this.drinkPrice += price+"원";
               }
          }
     }
}
