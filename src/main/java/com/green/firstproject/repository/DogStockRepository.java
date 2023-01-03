package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.DogStockEntity;

@Repository
public interface DogStockRepository extends JpaRepository<DogStockEntity,Long>{
    
}
