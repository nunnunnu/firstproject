package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.MemberInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//회원 로그인을 위한 VO

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO {
    private String email;
    private String pwd;
    public LoginUserVO(MemberInfoEntity account){
        this.email = account.getMiEmail();
        this.pwd = account.getMiPwd();
    } 
}
