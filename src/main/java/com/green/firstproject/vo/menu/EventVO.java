package com.green.firstproject.vo.menu;

import java.time.LocalDate;

import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;

import lombok.Data;

//삭제 보류. 장바구니 확정되면 삭제

@Data
public class EventVO {
    private Long eventSeq;
    private String eventName;
    private LocalDate eventStartDt;
    private LocalDate eventEndDt;
    private Integer eventPrice;
    private String eventDetail;
    private String eventFile;
    private String eventUri;

    public EventVO(EventInfoEntity event){
        this.eventSeq = event.getEiSeq();
        this.eventName = event.getEiName();
        this.eventStartDt = event.getEiStartDt();
        this.eventEndDt = event.getEiEndDt();
        // this.eventPrice = event.getEiPrice();
        // this.eventDetail = event.getEiDetail();
        this.eventFile = event.getEiFile();
        this.eventUri = event.getEiUri();
    }
}
