package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

@Repository
public interface BurgerInfoRepository extends JpaRepository<BurgerInfoEntity, Long> {
     BurgerInfoEntity findByBiSeq(Long seq);
     // List<BurgerInfoEntity> searchBurgerSeq(Long seq);
}
