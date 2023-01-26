package com.green.firstproject.vo.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//회원 정보 변경을 위한 VO

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateVO {
    private String pwd;
    private String changePwd;
    private String phone;
    private Integer gen;

}
