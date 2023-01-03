package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.OrderDetailCompositionEntity;

@Repository
public interface OrderDetailCompositionRepository extends JpaRepository<OrderDetailCompositionEntity,Long>{
    
}
