package com.green.firstproject.vo.store;

import com.green.firstproject.entity.master.StoreInfoEntity;

import lombok.Data;

@Data
public class StoreVO {
     private Long seq;
     private String name;

     public StoreVO(StoreInfoEntity store){
          this.seq = store.getSiSeq();
          this.name = store.getSiName();
     }    
}
