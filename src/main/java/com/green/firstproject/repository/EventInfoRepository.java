package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.EventInfoEntity;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfoEntity, Long>{
     
}
