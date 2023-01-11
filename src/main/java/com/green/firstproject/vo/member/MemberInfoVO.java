package com.green.firstproject.vo.member;

import java.time.LocalDate;

import com.green.firstproject.entity.member.GradeInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoVO {
    private Long memberSeq;
    private String memberEmail;
    private String memberPwd;
    private String memberName;
    private String memberPhone;
    private Integer memberGen;
    private LocalDate memberBirth;
    private Integer memberStatus;
    private GradeInfoEntity memberGrade;
    
    public MemberInfoVO(MemberInfoEntity member){
        this.memberSeq = member.getMiSeq();
        this.memberEmail = member.getMiEmail();
        this.memberPwd = member.getMiPwd();
        this.memberName = member.getMiName();
        this.memberPhone = member.getMiPhone();
        this.memberGen = member.getMiGen();
        this.memberBirth = member.getMiBirth();
        this.memberStatus = member.getMiStatus();
        this.memberGrade = member.getMiGrade();
    } 
}
