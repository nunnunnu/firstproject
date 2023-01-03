package com.green.firstproject.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.stock.EventStockEntity;

@Repository
public interface EventStockRepository extends JpaRepository<EventStockEntity,Long>{
    
}
