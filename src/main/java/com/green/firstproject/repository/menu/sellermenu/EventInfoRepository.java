package com.green.firstproject.repository.menu.sellermenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfoEntity, Long>{
     EventInfoEntity findByEiSeq(Long seq);

     public Integer countByEiName(String eiName);

     List<EventInfoEntity> findByCate(CategoryEntity cate);
}
