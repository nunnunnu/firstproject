package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;

import lombok.Data;

@Data
public class DrinkVO {
    private Long drinkSeq;
    private String drinkName;
    private String drinkDetail;
    private String drinkFile;
    private String drinkUri;

    public DrinkVO(DrinkInfoEntity drink){
        this.drinkSeq = drink.getDiSeq();
        this.drinkName = drink.getDiName();
        this.drinkDetail = drink.getDiDetail();
        this.drinkFile = drink.getDiFile();
        this.drinkUri = drink.getDiUri();
    }
}
