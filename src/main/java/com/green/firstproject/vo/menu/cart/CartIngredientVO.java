package com.green.firstproject.vo.menu.cart;

import com.green.firstproject.vo.menu.IngredientVo;

import lombok.Data;

@Data
public class CartIngredientVO {
     private Long   ingredientSeq;
     private String ingredientName;
     private String ingredientPrice;

     public CartIngredientVO(IngredientVo ingredient){
          this.ingredientSeq=ingredient.getIngredirentSeq();
          this.ingredientName=ingredient.getIngredientName();
          this.ingredientPrice="+"+ingredient.getIngredientPrice()+"원";
     }
}
