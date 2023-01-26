package com.green.firstproject.vo.member;

import java.time.LocalDate;

import com.green.firstproject.entity.member.MemberInfoEntity;

import lombok.Data;

//마이페이지 조회를 위한 VO

@Data
public class MemberMypageVO {
    private Long memberSeq;
    private String memberEmail;
    private String memberName;
    private String memberPhone;
    private Integer memberGen;
    private LocalDate memberBirth;
    // private Integer memberStatus;
    private String memberGrade;

    public MemberMypageVO(MemberInfoEntity member){
        this.memberSeq=member.getMiSeq();
        this.memberEmail=member.getMiEmail();
        this.memberName=member.getMiName();
        this.memberPhone=member.getMiPhone();
        this.memberGen=member.getMiGen();
        this.memberBirth=member.getMiBirth();
        // this.memberStatus=member.getMiStatus();
        this.memberGrade=member.getMiGrade().getGiName();
    }
}
