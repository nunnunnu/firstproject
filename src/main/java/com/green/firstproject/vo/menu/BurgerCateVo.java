package com.green.firstproject.vo.menu;

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