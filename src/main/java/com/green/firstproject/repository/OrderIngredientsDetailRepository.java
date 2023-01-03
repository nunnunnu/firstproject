package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.OrderIngredientsDetailEntity;

@Repository
public interface OrderIngredientsDetailRepository extends JpaRepository<OrderIngredientsDetailEntity, Long> {
    
}
