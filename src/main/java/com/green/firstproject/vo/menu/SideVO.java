package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;

import lombok.Data;

@Data
public class SideVO {
    private Long sideSeq;
    private String sideName;
    private String sideDetail;
    private String sideFile;
    private String sideUri;

    public SideVO(SideInfoEntity side){
        this.sideSeq = side.getSideSeq();
        this.sideName = side.getSideName();
        this.sideDetail = side.getSideDetail();
        this.sideFile = side.getSideDetail();
        this.sideUri = side.getSideUri();
    }
}
