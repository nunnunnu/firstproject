package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.LatelyDeliveryEntity;
import com.green.firstproject.entity.member.MyDeliveryEntity;

import lombok.Data;

//최근 배달지, 마이 배달지 조회를 위한 VO

@Data
public class DeliveryVO {
     private String address;
     private Integer basic;

     public DeliveryVO(LatelyDeliveryEntity late){
          this.address = late.getLdAddress() + " "+late.getLdDetailAddress();
     }

     public DeliveryVO(MyDeliveryEntity my){
          this.address = my.getMdAddress() + " "+my.getMdDetailAddress();
          this. basic = my.getMdBasic();
     }
}
