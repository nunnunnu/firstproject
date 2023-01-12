package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

import lombok.Data;

@Data
public class BestMenuVO {
    private String name;
    private Integer salesRate;

    public BestMenuVO(BurgerInfoEntity burger){
        this.name = burger.getBiName();
        this.salesRate = burger.getBiSalesRate();
    }
}
