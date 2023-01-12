package com.green.firstproject.vo.menu;

import java.time.LocalDate;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

import lombok.Data;

@Data
public class BurgerCateVo {
    private Long burgerSeq;
    private String burgerName;
    private String burgerDetail;
    private String burgerFile;
    private String burgerUri;
    private LocalDate burgerRegDt;
    private Integer burgerSalesRate;
    private Boolean burgerBest;

    public BurgerCateVo(BurgerInfoEntity burger, Integer ranking){
        this.burgerSeq=burger.getBiSeq();
        this.burgerName=burger.getBiName();
        this.burgerDetail=burger.getBiDetail();
        this.burgerFile=burger.getBiFile();
        this.burgerUri=burger.getBiUri();
        this.burgerSalesRate=burger.getBiSalesRate();
        this.burgerRegDt=burger.getBiRegDt();
        // this.ranking=ranking;
        best(ranking);

    }
    public void best(Integer rank){
        if(rank<=10){
            this.burgerBest=true;
        }else{
            this.burgerBest=false;
        }
    }

}
