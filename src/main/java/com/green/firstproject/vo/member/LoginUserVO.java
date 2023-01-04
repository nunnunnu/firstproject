package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.MemberInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO {
    // private Long seq;
    private String email;
    private String pwd;
    // private String name;
    // private String phone;
    // private Integer gen;
    // private LocalDate birth;
    // private Integer status;
    // private GradeInfoEntity grade;
    public LoginUserVO(MemberInfoEntity account){
        // this.seq = account.getMiSeq();
        this.email = account.getMiEmail();
        this.pwd = account.getMiPwd();
        // this.name = account.getMiName();
        // this.phone = account.getMiPhone();
        // this.gen = account.getMiGen();
        // this.birth = account.getMiBirth();
        // this.status = account.getMiStatus();
        // this.grade = account.getMiGrade();
    } 
}
