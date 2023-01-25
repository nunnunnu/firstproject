package com.green.firstproject.vo.menu;

import java.time.LocalDate;

public interface BurgerCateVo {

    Long getBurgerSeq();
    String getBurgerName();
    LocalDate getBurgerRegDt();
    String getBurgerDetail();
    String getBurgerFile();
    String getBurgerUri();
    Integer getPrice();
    boolean getBurgerNew();
    boolean getBurgerBest();
    boolean getBurgerSoldout();
    Integer getCount();
    Long getCategory();
}