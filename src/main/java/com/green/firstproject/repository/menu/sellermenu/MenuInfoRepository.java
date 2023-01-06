package com.green.firstproject.repository.menu.sellermenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

@Repository
public interface MenuInfoRepository extends JpaRepository<MenuInfoEntity, Long>{
     MenuInfoEntity findByMenuSeq(Long seq);
     List<MenuInfoEntity> findByBurger(BurgerInfoEntity burger);
     List<MenuInfoEntity> findBydog(DogInfoEntity dog);
}
