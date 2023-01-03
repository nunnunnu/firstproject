package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.MenuInfoEntity;

@Repository
public interface MenuInfoRepository extends JpaRepository<MenuInfoEntity, Long>{
    
}
