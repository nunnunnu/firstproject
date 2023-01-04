package com.green.firstproject.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.stock.SideStockEntity;

@Repository
public interface SideStockRepository extends JpaRepository<SideStockEntity,Long>{
     SideStockEntity findByStoreAndSide(StoreInfoEntity store, SideInfoEntity side);
}
