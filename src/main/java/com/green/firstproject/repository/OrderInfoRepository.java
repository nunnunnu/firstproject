package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.OrderInfoEntity;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{
    
}
