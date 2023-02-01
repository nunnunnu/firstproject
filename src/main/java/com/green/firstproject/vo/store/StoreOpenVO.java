package com.green.firstproject.vo.store;

import java.time.LocalTime;

import com.green.firstproject.entity.master.StoreInfoEntity;

import lombok.Data;

@Data
public class StoreOpenVO {
    private Long seq;
    private String name;
    private LocalTime open;
    private LocalTime close;
    private Boolean openStatus;

    public StoreOpenVO(StoreInfoEntity entity){
        this.seq = entity.getSiSeq();
        this.name = entity.getSiName();
        this.open = entity.getSiOpenTime();
        this.close = entity.getSiCloseTime();
        if(LocalTime.now().isAfter(open) && LocalTime.now().isBefore(close) && entity.getSiStatus()==1){
            this.openStatus=true;
        }else{
            this.openStatus=false;
        }
    }
}
    