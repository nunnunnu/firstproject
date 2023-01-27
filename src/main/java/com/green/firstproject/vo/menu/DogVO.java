package com.green.firstproject.vo.menu;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

import lombok.Data;

////판매메뉴 조회 용 VO

@Data
public class DogVO {
    private Long seq;
    private String name;
    private String detail;
    private String uri;
    private List<SellerVO> seller = new ArrayList<>();

    public DogVO(DogInfoEntity dog){
        this.seq = dog.getDogSeq();
        this.name = dog.getDogName();
        this.detail = dog.getDogDetail();
        this.uri = dog.getDogFile();
    }
    public void changeSeller(List<MenuInfoEntity> list){
        List<SellerVO> result = new ArrayList<>();
        for(MenuInfoEntity m : list){
            result.add(new SellerVO(m));
        }
        this.seller.addAll(result);
    }
}
