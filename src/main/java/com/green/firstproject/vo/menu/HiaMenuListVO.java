package com.green.firstproject.vo.menu;

import java.time.LocalDate;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

import lombok.Data;

@Data
public class HiaMenuListVO {
  private String name;
  private Long cate;
  private String detail;
  private String file;
  private String uri;
  private LocalDate regDt;
  private Integer salesRate;
  private Boolean status;

  public HiaMenuListVO(BurgerInfoEntity data){
    this.name = data.getBiName();
    this.detail = data.getBiDetail();
    this.file = data.getBiFile();
    this.uri = data.getBiUri();
    this.regDt = data.getBiRegDt();
    this.salesRate = data.getBiSalesRate();
  }

  public void setCategory(CategoryEntity cate){
    this.cate = cate.getCateSeq();
  }
}
