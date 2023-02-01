package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.MemberInfoEntity;

import lombok.Data;

@Data
public class LoginSession {
     private Long seq;
     private String email;
     private String name;
     
     public LoginSession(MemberInfoEntity member){
          this.seq = member.getMiSeq();
          this.email = member.getMiEmail();
          this.name = member.getMiName();
     }

}
