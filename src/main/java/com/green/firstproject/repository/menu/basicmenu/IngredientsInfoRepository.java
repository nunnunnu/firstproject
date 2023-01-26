package com.green.firstproject.repository.menu.basicmenu;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

@Repository
public interface IngredientsInfoRepository extends JpaRepository<IngredientsInfoEntity, Long>{

     @Query("select i from IngredientsInfoEntity i join fetch i.menu m where i.iiSeq in (:ingSeqs)")
     public List<IngredientsInfoEntity> findByingSeq(@Param("ingSeqs") Collection<Long> seqs);

     @Query("select i from IngredientsInfoEntity i join fetch i.menu m where i.iiSeq in (:ingSeqs)")
     public List<IngredientsInfoEntity> findByings(@Param("ingSeqs") Collection<IngredientsInfoEntity> seqs);
     
     // @Query("select i from IngredientsInfoEntity i where i.iiSeq in (:ings)")
     // public Set<IngredientsInfoEntity> findBying(@Param("ings") Collection<IngredientsInfoEntity> ings);

     public IngredientsInfoEntity findByIiSeq(Long seq);

     public Integer countByIiName(String iiName);

     @Query("select i from IngredientsInfoEntity i where i.menu=:menu or i.menu is null")
     public List<IngredientsInfoEntity> showIngredient(@Param("menu") MenuInfoEntity menu);
     
}
