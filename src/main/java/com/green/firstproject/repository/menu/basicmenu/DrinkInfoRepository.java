package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;

@Repository
public interface DrinkInfoRepository extends JpaRepository<DrinkInfoEntity, Long> {
    List<DrinkInfoEntity> findByCate(CategoryEntity cate);
     public Integer countByDiName(String diName);
}
