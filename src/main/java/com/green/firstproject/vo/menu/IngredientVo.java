package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;

import lombok.Data;

//삭제 보류. 장바구니 확정되면 삭제

@Data
public class IngredientVo {
    private Long ingredirentSeq;
    private String ingredientName;
    private Integer ingredientPrice;
    private String ingredientFile;

    public IngredientVo(IngredientsInfoEntity ingredient){
        this.ingredirentSeq=ingredient.getIiSeq();
        this.ingredientPrice=ingredient.getIiPrice();
        this.ingredientName=ingredient.getIiName();
        this.ingredientFile=ingredient.getIiFile();
    }

    public IngredientVo(String name, Integer price) {
        this.ingredientName = name;
        this.ingredientPrice=price;
    }
    
}
