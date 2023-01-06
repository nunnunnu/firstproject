package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
@Repository
public interface SideInfoRepository extends JpaRepository<SideInfoEntity, Long> {
    List <SideInfoEntity> findByCate(CategoryEntity cate);
        public Integer countBySideName(String sideName);
}
