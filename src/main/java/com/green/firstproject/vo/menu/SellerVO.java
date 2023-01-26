package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

import lombok.Data;

//판매 메뉴 리스트 조회 시 사용할 VO
@Data
public class SellerVO {
     private Long seq;
     private String name;
     private Integer price;
     private String uri;
     private String explain;
     private Boolean ingredientSelect;
     private MenuKind menuKind;

     public SellerVO(MenuInfoEntity menu){
          this.seq = menu.getMenuSeq();
          this.name = menu.getMenuName();
          this.price = menu.getMenuPrice();
          this.uri = menu.getMenuUri();
          this.explain = menu.getMenuEx();
          this.ingredientSelect = menu.getMenuSelect();
          
          if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
               this.menuKind = MenuKind.SET;
          }else if(menu.getEvent()!=null){
               this.menuKind = MenuKind.EVENT;
          }else{
               this.menuKind=MenuKind.SINGLE;
          }
     }
}
