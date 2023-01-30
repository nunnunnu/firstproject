package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.AdminInfoEntity;

import lombok.Data;

@Data
public class AdminLoginVO {
    private String adminEmail;
    private String adminPwd;

    public void AdminInfoVO(AdminInfoEntity entity){
        this.adminEmail = entity.getAdminEmail();
        this.adminPwd = entity.getAdminPwd();
    }
}
