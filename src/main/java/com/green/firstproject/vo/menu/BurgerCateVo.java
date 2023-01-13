package com.green.firstproject.vo.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BurgerCateVo {
    private String burgerSeq;
    private String burgerName;
    private String burgerDetail;
    private String burgerFile;
    private String burgerUri;
    // private LocalDateTime burgerRegDt;
    @JsonIgnore
    private Integer burgerSalesRate;
    private Boolean burgerBest;

    public BurgerCateVo( 
        String burgerSeq,
        String burgerName,
        String burgerDetail,
        String burgerFile,
        String burgerUri,
        // LocalDateTime burgerRegDt,
        Integer burgerSalesRate,
        Integer ranking
    ){
        this.burgerSeq=burgerSeq;
        this.burgerName=burgerName;
        this.burgerDetail=burgerDetail;
        this.burgerFile=burgerFile;
        this.burgerUri=burgerUri;
        this.burgerSalesRate=burgerSalesRate;
        // this.burgerRegDt=burgerRegDt;
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
