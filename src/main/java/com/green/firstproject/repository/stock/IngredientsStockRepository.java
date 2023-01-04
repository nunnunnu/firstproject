package com.green.firstproject.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;

@Repository
public interface IngredientsStockRepository extends JpaRepository<IngredientsStockEntity, Long>{
     IngredientsInfoEntity findByStoreAndIngredient(StoreInfoEntity store, IngredientsInfoEntity ingredient);
}
