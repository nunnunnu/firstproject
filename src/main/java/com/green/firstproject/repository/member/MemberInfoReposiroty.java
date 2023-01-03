<<<<<<< HEAD:src/main/java/com/green/firstproject/repository/member/MemberInfoReposiroty.java
package com.green.firstproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.MemberInfoEntity;

@Repository
public interface MemberInfoReposiroty extends JpaRepository<MemberInfoEntity, Long> {
     MemberInfoEntity findByMiSeq(Long seq);
}
=======
package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.MemberInfoEntity;

@Repository
public interface MemberInfoReposiroty extends JpaRepository<MemberInfoEntity, Long> {
    public Integer countByMiEmail(String miEmail);
}
>>>>>>> hia:src/main/java/com/green/firstproject/repository/MemberInfoReposiroty.java
