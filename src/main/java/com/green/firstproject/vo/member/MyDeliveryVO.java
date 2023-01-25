package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.MyDeliveryEntity;

import lombok.Data;

@Data
public class MyDeliveryVO {
    private String address;

    public MyDeliveryVO(MyDeliveryEntity my){
        this.address = my.getMdAddress() + " "+my.getMdDetailAddress();
    }
}
