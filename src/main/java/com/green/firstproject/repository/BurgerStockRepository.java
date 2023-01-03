package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.BurgetStockEntity;

@Repository
public interface BurgerStockRepository extends JpaRepository<BurgetStockEntity,Long>{
    
}
