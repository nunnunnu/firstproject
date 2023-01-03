package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.MemberInfoEntity;

@Repository
public interface MemberInfoReposiroty extends JpaRepository<MemberInfoEntity, Long> {
    
}
