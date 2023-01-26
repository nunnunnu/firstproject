package com.green.firstproject.vo.menu;

import java.time.LocalDate;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

import lombok.Data;

//삭제 보류. 장바구니 확정되면 삭제

@Data
public class BurgerVO {
    private Long burgerSeq;
    private String burgerName;
    private String burgerDetail;
    private String burgerFile;
    private String burgerUri;
    private LocalDate burgerRegDt;
    private Integer burgerSalesRate;
    private Boolean burgerBest;
    private Boolean burgerNew;

    public BurgerVO(BurgerInfoEntity burger){
        this.burgerSeq = burger.getBiSeq();
        this.burgerName=burger.getBiName();
        this.burgerDetail= burger.getBiDetail();
        this.burgerFile=burger.getBiFile();
        this.burgerUri=burger.getBiUri();
        this.burgerRegDt=burger.getBiRegDt();
        this.burgerSalesRate=burger.getBiSalesRate();
    }
}
