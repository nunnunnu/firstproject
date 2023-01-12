package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.LatelyDeliveryEntity;

import lombok.Data;

@Data
public class LatelyDeliveryVO {
     private String address;
     // private String detailAddress;

     public LatelyDeliveryVO(LatelyDeliveryEntity late){
          this.address = late.getLdAddress() + " "+late.getLdDetailAddress();
     }
}
