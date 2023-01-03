package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.DrinkStockEntity;

@Repository
public interface DrinkStockRepository extends JpaRepository<DrinkStockEntity, Long>{
    
}
