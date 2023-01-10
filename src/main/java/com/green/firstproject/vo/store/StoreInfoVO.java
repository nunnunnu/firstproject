package com.green.firstproject.vo.store;

import java.time.LocalTime;

import com.green.firstproject.entity.master.StoreInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreInfoVO {
    private long seq;
    private String phone;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer minOrderAmount;
    private Integer status;
    public StoreInfoVO(StoreInfoEntity storeinfo){
        this.seq = storeinfo.getSiSeq();
        this.phone =storeinfo.getSiPhone();
        this.openTime=storeinfo.getSiOpenTime();
        this.closeTime=storeinfo.getSiCloseTime();
        this.minOrderAmount=storeinfo.getSiMinOrderAmount();
        this.status = getStatus();
}
}
