package com.green.firstproject.vo.menu;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

import lombok.Data;

//판매메뉴 조회 용 VO

@Data
public class EventVO {
    private Long seq;
    private String name;
    private String detail;
    private String uri;
    private List<SellerVO> seller = new ArrayList<>();

    public EventVO(EventInfoEntity event){
        this.seq = event.getEiSeq();
        this.detail = event.getEiDetail();
        this.name = event.getEiName();
        this.uri = event.getEiUri();
    }
    public void changeSeller(List<MenuInfoEntity> list){
        List<SellerVO> result = new ArrayList<>();
        for(MenuInfoEntity m : list){
            result.add(new SellerVO(m));
        }
        this.seller.addAll(result);
    }
}
