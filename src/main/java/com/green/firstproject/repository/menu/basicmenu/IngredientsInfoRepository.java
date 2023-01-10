package com.green.firstproject.repository.menu.basicmenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;

@Repository
public interface IngredientsInfoRepository extends JpaRepository<IngredientsInfoEntity, Long>{
     IngredientsInfoEntity findByIiSeq(Long seq);
     public Integer countByIiName(String iiName);
     
}
