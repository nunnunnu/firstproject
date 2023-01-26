package com.green.firstproject.vo.menu.cart;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;

import lombok.Data;

@Data
public class CartIngredientVO {
     private Long   ingredientSeq;
     private String ingredientName;
     private Integer ingredientPrice;

     public CartIngredientVO(IngredientsInfoEntity ingredient){
          this.ingredientSeq=ingredient.getIiSeq();
          this.ingredientName=ingredient.getIiName();
          this.ingredientPrice=ingredient.getIiPrice();
     }

     public CartIngredientVO(String name, Integer price){
          this.ingredientName=name;
          this.ingredientPrice=price;
     }
}
