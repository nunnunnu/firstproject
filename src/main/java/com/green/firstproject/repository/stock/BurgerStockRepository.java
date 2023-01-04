package com.green.firstproject.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.stock.BurgerStockEntity;

@Repository
public interface BurgerStockRepository extends JpaRepository<BurgerStockEntity,Long>{
     BurgerStockEntity findByStoreAndBurger(StoreInfoEntity store, BurgerInfoEntity burger);
}
