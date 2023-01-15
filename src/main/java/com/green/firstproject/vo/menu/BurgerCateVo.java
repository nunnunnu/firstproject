package com.green.firstproject.vo.menu;

import java.time.LocalDateTime;

public interface BurgerCateVo {

    Long getBurgerSeq();
    String getBurgerName();
    String getBurgerDetail();
    String getBurgerFile();
    String getBurgerUri();
    LocalDateTime getBurgerRegDt();
    boolean getBurgerBest();
    String getRanking();


}
