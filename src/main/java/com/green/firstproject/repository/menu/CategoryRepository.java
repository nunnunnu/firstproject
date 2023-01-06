package com.green.firstproject.repository.menu;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    CategoryEntity findByCateSeq(Long cateSeq);
    
}
