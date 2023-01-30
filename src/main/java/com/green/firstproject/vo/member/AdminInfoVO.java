package com.green.firstproject.vo.member;

import com.green.firstproject.entity.member.AdminInfoEntity;

import lombok.Data;

@Data
public class AdminInfoVO {
    private Long adminNo;
    private String adminEmail;
    private String adminName;
    private Long adminGrade;
    private Long adminStore;

    public AdminInfoVO(AdminInfoEntity entity){
        this.adminNo = entity.getAdminSeq();
        this.adminEmail = entity.getAdminEmail();
        this.adminName = entity.getAdminName();
        this.adminGrade = entity.getAdminGrade().getGiSeq();
        this.adminStore = entity.getStore().getSiSeq();
    }
}
