package com.green.firstproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.AdminInfoEntity;

@Repository
public interface AdminInfoRepository extends JpaRepository<AdminInfoEntity, Long>{
    AdminInfoEntity findByAdminEmailAndAdminPwd(String adminEmail, String adminPwd);
}
