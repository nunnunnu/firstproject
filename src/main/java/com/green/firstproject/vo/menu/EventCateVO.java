package com.green.firstproject.vo.menu;

//카테고리 조회(이벤트메뉴용)를 위한 VO - projection

public interface EventCateVO {
     Long getSeq();
     String getName();
     String getDetail();
     String getUri();
     Integer getCount();
     Integer getPrice();
     Boolean getSoldout();
     
}
