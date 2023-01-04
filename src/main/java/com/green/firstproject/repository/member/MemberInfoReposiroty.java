package com.green.firstproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.MemberInfoEntity;

@Repository
public interface MemberInfoReposiroty extends JpaRepository<MemberInfoEntity, Long> {
     public MemberInfoEntity findByMiSeq(Long seq);
     Integer countByMiEmail(String miEmail);
     Integer countByMiNameAndMiPhone(String miName, String miPhone);
     Integer countByMiNameAndMiEmail(String miName, String miEmail);
}
