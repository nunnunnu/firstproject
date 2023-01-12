package com.green.firstproject.vo.menu.option;

import com.green.firstproject.entity.menu.option.SideOptionEntity;

import lombok.Data;

@Data
public class SideOptionVO {
    private Long sideOptSeq;
    private String sideOptName;
    private int sideOptPrice;
    private int sideOptSize;
    private String sideOptFile;
    private String sideOptUri;

    public SideOptionVO(SideOptionEntity side){
        this.sideOptSeq=side.getSoSeq();
        this.sideOptName=side.getSoName();
        this.sideOptPrice=side.getSoPrice();
        this.sideOptSize=side.getSoSize();
        this.sideOptFile=side.getSoFile();
        this.sideOptUri=side.getSoUri();
    }
}
