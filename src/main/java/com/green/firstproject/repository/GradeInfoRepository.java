package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.GradeInfoEntity;

@Repository
public interface GradeInfoRepository extends JpaRepository<GradeInfoEntity, Long> {
    
}
