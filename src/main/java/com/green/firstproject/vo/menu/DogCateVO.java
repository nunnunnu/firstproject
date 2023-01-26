package com.green.firstproject.vo.menu;

//카테고리 조회(독퍼용)를 위한 VO - projection

public interface DogCateVO {
     Long getSeq();
     String getName();
     String getDetail();
     String getUri();
     Integer getCount();
     Integer getPrice();
     Boolean getSoldout();
}
