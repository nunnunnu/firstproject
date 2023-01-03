package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.IngredientsInfoEntity;

@Repository
public interface IngredientsInfoRepository extends JpaRepository<IngredientsInfoEntity, Long>{
    
}
