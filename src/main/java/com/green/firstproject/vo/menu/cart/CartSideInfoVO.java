package com.green.firstproject.vo.menu.cart;

import com.green.firstproject.entity.menu.option.SideOptionEntity;

import lombok.Data;

@Data
public class CartSideInfoVO {
     private Long sideSeq;
     private String sideName;
     private Integer sidePrice;

     public CartSideInfoVO(SideOptionEntity side, Integer menuSize){
          if(side!=null){
               this.sidePrice=0;
               this.sideSeq = side.getSoSeq();
               this.sideName = side.getSoName();
               if(menuSize == 1){
                    this.sidePrice = side.getSoPrice()-2700;
               }else if(menuSize==2){
                    this.sidePrice = side.getSoPrice()-3200;
               }
          }
     }
     public CartSideInfoVO(SideOptionEntity side){
          this.sideSeq = side.getSoSeq();
          this.sideName = side.getSoName();
          this.sidePrice= side.getSoPrice()-2600;
     }

}
