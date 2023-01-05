package com.green.firstproject.repository.menu.sellermenu;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

@Repository
public interface MenuInfoRepository extends JpaRepository<MenuInfoEntity, Long>{
    
}
