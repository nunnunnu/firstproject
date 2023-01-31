package com.green.firstproject.vo.menu;

//카테고리 조회(음료용)를 위한 VO - projection

public interface DrinkCateVO {

     Long getSeq();
     String getName();
     String getDetail();
     String getUri();
     Integer getCount();
     Integer getPrice();
     Boolean getSoldout();
     String getType();
}
