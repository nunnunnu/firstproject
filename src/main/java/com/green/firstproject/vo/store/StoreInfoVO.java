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
    private long storeSeq;
    private String storeName;
    private String storeAddress;
    private String storePhone;
    private LocalTime storeOpenTime;
    private LocalTime storeCloseTime;
    private Integer storeMinOrderAmount;
    private Integer storeStatus;
    public StoreInfoVO(StoreInfoEntity storeinfo){
        this.storeSeq = storeinfo.getSiSeq();
        this.storeName = storeinfo.getSiName();
        this.storeAddress = storeinfo.getSiAddress();
        this.storePhone =storeinfo.getSiPhone();
        this.storeOpenTime=storeinfo.getSiOpenTime();
        this.storeCloseTime=storeinfo.getSiCloseTime();
        this.storeMinOrderAmount=storeinfo.getSiMinOrderAmount();
        this.storeStatus =storeinfo.getSiStatus();
}
}
