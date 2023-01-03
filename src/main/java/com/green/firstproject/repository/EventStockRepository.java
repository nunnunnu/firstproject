package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.EventStockEntity;

@Repository
public interface EventStockRepository extends JpaRepository<EventStockEntity,Long>{
    
}
