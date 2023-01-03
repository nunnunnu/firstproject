package com.green.firstproject.repository.menu.basicmenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;

@Repository
public interface DrinkInfoRepository extends JpaRepository<DrinkInfoEntity, Long> {
     
}
