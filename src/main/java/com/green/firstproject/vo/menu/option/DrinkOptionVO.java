package com.green.firstproject.vo.menu.option;

import com.green.firstproject.entity.menu.option.DrinkOptionEntity;

import lombok.Data;

@Data
public class DrinkOptionVO {
    private Long drinkOptSeq;
    private String drinkOptName;
    private int drinkOptPrice;
    private int drinkOptSize;
    private String drinkOptFile;
    private String drinkOptUri;

    public DrinkOptionVO(DrinkOptionEntity drink){
        this.drinkOptSeq=drink.getDoSeq();
        this.drinkOptName=drink.getDoName();
        this.drinkOptPrice=drink.getDoPrice();
        this.drinkOptSize=drink.getDoSize();
        this.drinkOptFile=drink.getDoFile();
        this.drinkOptUri=drink.getDoUri();
    }
}
