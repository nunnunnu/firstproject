package com.green.firstproject.vo.menu;

//카테고리 조회(버거용)를 위한 VO - projection

public interface BurgerCateVo {

    Long getSeq();
    String getName();
    String getDetail();
    String getUri();
    boolean getNew();
    boolean getBest();
    Integer getPrice();
    Integer getCount();
    boolean getSoldout();
}