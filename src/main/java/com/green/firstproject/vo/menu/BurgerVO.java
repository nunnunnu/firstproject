package com.green.firstproject.vo.menu;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

import lombok.Data;

//판매메뉴 조회 용 VO

@Data
public class BurgerVO {
    private Long seq;
    private String name;
    private String detail;
    private String uri;
    private List<SellerVO> seller = new ArrayList<>();

    public BurgerVO(BurgerInfoEntity burger){
        this.seq = burger.getBiSeq();
        this.name=burger.getBiName();
        this.detail= burger.getBiDetail();
        this.uri=burger.getBiUri();
    }

    public void changeSeller(List<MenuInfoEntity> list){
        List<SellerVO> result = new ArrayList<>();
        for(MenuInfoEntity m : list){
            result.add(new SellerVO(m));
        }
        this.seller.addAll(result);
    }
}
