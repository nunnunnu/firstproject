package com.green.firstproject.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.stock.DrinkStockEntity;
import com.green.firstproject.vo.stock.BurgerStockVO;

@Repository
public interface DrinkStockRepository extends JpaRepository<DrinkStockEntity, Long>{
     DrinkStockEntity findByStoreAndDrink(StoreInfoEntity store, DrinkInfoEntity drink);

     @Query(value="select a.di_seq as seq , a.di_name as name ,if(ifnull(c.ds_stock,0)>=1,'판매 가능', '판매 불가') as stock from (select * from drink_stock b where b.ds_si_seq = :store ) c right outer join drink_info a on a.di_seq = c.ds_di_seq"
          ,nativeQuery = true)
     List<BurgerStockVO> stockAll(@Param("store") Long store);
}
