package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;

import lombok.Getter;

@Getter
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
    
}
