package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.member.MyDeliveryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// 마이 배달지 등록 VO
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyDeliveryVO {
    // private Long seq;
    private Long seq;
    private String address;
    private String detailAddress;
    private String name;
    private Integer basic;

    public MyDeliveryVO(MyDeliveryEntity my){
        // this.seq = my.getMdSeq();
        this.seq = my.getMember().getMiSeq();
        this.address = my.getMdAddress();
        this.detailAddress = my.getMdDetailAddress();
        this.name = my.getMdName();
        this.basic = my.getMdBasic();
    }
}
