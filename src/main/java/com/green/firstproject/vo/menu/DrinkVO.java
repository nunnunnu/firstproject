package com.green.firstproject.vo.menu;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

import lombok.Data;

//판매메뉴 조회 용 VO

@Data
public class DrinkVO {
    private Long seq;
    private String name;
    private String detail;
    private String uri;
    private List<SellerVO> seller = new ArrayList<>();

    public DrinkVO(DrinkInfoEntity drink){
        this.seq = drink.getDiSeq();
        this.name = drink.getDiName();
        this.detail = drink.getDiDetail();
        this.uri = drink.getDiUri();
    }
    public void changeSeller(List<MenuInfoEntity> list){
        List<SellerVO> result = new ArrayList<>();
        for(MenuInfoEntity m : list){
            result.add(new SellerVO(m));
        }
        this.seller.addAll(result);
    }
}
