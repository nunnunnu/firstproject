package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.SideInfoEntity;
@Repository
public interface SideInfoRepository extends JpaRepository<SideInfoEntity, Long> {
        
}
