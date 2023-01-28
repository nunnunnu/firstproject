package com.green.firstproject.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.vo.stock.BurgerStockVO;

@Repository
public interface BurgerStockRepository extends JpaRepository<BurgerStockEntity,Long>{
     BurgerStockEntity findByStoreAndBurger(StoreInfoEntity store, BurgerInfoEntity burger);

     @Query(value="select bi.bi_seq as seq , bi.bi_name as name ,ifnull(bs.bs_stock,0) as stock from (select * from burger_stock b where b.bs_si_seq = :store ) bs right outer join burger_info bi on bi.bi_seq = bs.bs_bi_seq"
          ,nativeQuery = true)
     List<BurgerStockVO> stockAll(@Param("store") Long store);
}
