package com.green.firstproject.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.GradeInfoEntity;

@Repository
public interface GradeInfoRepository extends JpaRepository<GradeInfoEntity, Long> {
    
}
