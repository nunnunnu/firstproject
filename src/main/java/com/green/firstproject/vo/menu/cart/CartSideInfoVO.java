package com.green.firstproject.vo.menu.cart;

import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;

import lombok.Data;

@Data
public class CartSideInfoVO {
     private Long sideSeq;
     private String sideName;
     private String sidePrice;

     public CartSideInfoVO(SideOptionEntity side, Integer menuSize){
          this.sideSeq = side.getSoSeq();
          this.sideName = side.getSoName();
          this.sidePrice="+";
          if(menuSize == 1){
               int price = side.getSoPrice()-2700;
               if(price<0){
                    this.sidePrice += "0원";

               }else{
                    this.sidePrice += price+"원";
               }
          }else if(menuSize==2){
               int price = side.getSoPrice()-3200;
               if(price<0){
                    this.sidePrice += "0원";

               }else{
                    this.sidePrice += price+"원";
               }
          }
     }

}
