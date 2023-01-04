package com.green.firstproject.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.stock.DogStockEntity;

@Repository
public interface DogStockRepository extends JpaRepository<DogStockEntity,Long>{
     DogStockEntity findByStoreAndDog(StoreInfoEntity store, DogInfoEntity dog);
}
