package com.green.firstproject.repository.menu.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.option.DrinkOptionEntity;

@Repository
public interface DrinkOptionRepository extends JpaRepository<DrinkOptionEntity,Long>{
    
}
