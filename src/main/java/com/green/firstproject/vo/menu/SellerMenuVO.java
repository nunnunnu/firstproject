package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.vo.menu.option.DrinkOptionVO;
import com.green.firstproject.vo.menu.option.SideOptionVO;

public class SellerMenuVO {
     private String menuName;
     private Integer menuPrice;
     private String menuUri;
     private String menuEx;
     private SideOptionVO side;
     private DrinkOptionVO drink;
     private DrinkOptionVO drink2;
     private Integer menuSize;
     private Boolean menuSelect;
     
     public SellerMenuVO(MenuInfoEntity menu, SideOptionVO side, DrinkOptionVO drink, DrinkOptionVO drink2){
          this.menuName = menu.getMenuName();
          this.menuPrice = menu.getMenuPrice();
          this.menuUri = menu.getMenuUri();
          this.menuEx = menu.getMenuEx();
          this.side = side;
          this.drink = drink;
          this.drink2 = drink2;
          this.menuSize = menu.getMenuSize();
          this.menuSelect = menu.getMenuSelect();
     }
}
