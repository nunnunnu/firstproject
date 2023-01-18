package com.green.firstproject.repository.stock;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;

@Repository
public interface IngredientsStockRepository extends JpaRepository<IngredientsStockEntity, Long>{
     IngredientsStockEntity findByStoreAndIngredient(StoreInfoEntity store, IngredientsInfoEntity ingredient);

     @Query("select is from IngredientsStockEntity is join fetch is.ingredient i where store=:store and i.iiSeq in (:ingSeqs)")
     List<IngredientsStockEntity> findStoreAndIngredient(@Param("store") StoreInfoEntity store, @Param("ingSeqs") Collection<Long> seqs);
}
