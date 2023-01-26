package com.green.firstproject.vo.menu;

import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;

import lombok.Data;

//삭제 보류. 장바구니 확정되면 삭제

@Data
public class DogVO {
    private Long dogSeq;
    private String dogName;
    private String dogDetail;
    private String dogFile;
    private String dogUri;

    public DogVO(DogInfoEntity dog){
        this.dogSeq = dog.getDogSeq();
        this.dogName = dog.getDogName();
        this.dogDetail = dog.getDogDetail();
        this.dogFile = dog.getDogFile();
        this.dogUri = dog.getDogFile();
    }
}
