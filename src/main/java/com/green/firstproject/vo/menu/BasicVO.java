package com.green.firstproject.vo.menu;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

import lombok.Data;

//판매메뉴 조회 용 VO

@Data
public class BasicVO {
     private Long seq;
     private String name;
     private String detail;
     private String uri;
     private List < SellerVO > seller = new ArrayList < > ();

     public BasicVO(BurgerInfoEntity burger) {
          this.seq = burger.getBiSeq();
          this.name = burger.getBiName();
          this.detail = burger.getBiDetail();
          this.uri = burger.getBiUri();
     }

     public BasicVO(DogInfoEntity dog) {
          this.seq = dog.getDogSeq();
          this.name = dog.getDogName();
          this.detail = dog.getDogDetail();
          this.uri = dog.getDogFile();
     }

     public BasicVO(DrinkInfoEntity drink) {
          this.seq = drink.getDiSeq();
          this.name = drink.getDiName();
          this.detail = drink.getDiDetail();
          this.uri = drink.getDiUri();
     }

     public BasicVO(EventInfoEntity event) {
          this.seq = event.getEiSeq();
          this.detail = event.getEiDetail();
          this.name = event.getEiName();
          this.uri = event.getEiUri();
     }

     public BasicVO(SideInfoEntity side) {
          this.seq = side.getSideSeq();
          this.name = side.getSideName();
          this.detail = side.getSideDetail();
          this.uri = side.getSideUri();
     }

     public void changeSeller(List < MenuInfoEntity > list) {
          List < SellerVO > result = new ArrayList < > ();
          for (MenuInfoEntity m: list) {
               result.add(new SellerVO(m));
          }
          this.seller.addAll(result);
     }
}