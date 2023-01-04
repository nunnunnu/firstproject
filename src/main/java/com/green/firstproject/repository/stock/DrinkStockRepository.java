package com.green.firstproject.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.stock.DrinkStockEntity;

@Repository
public interface DrinkStockRepository extends JpaRepository<DrinkStockEntity, Long>{
     DrinkStockEntity findByStoreAndDrink(StoreInfoEntity store, DrinkInfoEntity drink);
}
