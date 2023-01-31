package com.green.firstproject.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.stock.SideStockEntity;
import com.green.firstproject.vo.stock.BurgerStockVO;

@Repository
public interface SideStockRepository extends JpaRepository<SideStockEntity,Long>{
     SideStockEntity findByStoreAndSide(StoreInfoEntity store, SideInfoEntity side);

     @Query(value="select a.side_seq as seq , a.side_name as name ,if(ifnull(c.ss_stock,0)>=1,'판매 가능', '판매 불가') as stock from (select * from side_stock b where b.ss_si_seq = :store ) c right outer join side_info a on a.side_seq = c.ss_side_seq"
          ,nativeQuery = true)
     List<BurgerStockVO> stockAll(@Param("store") Long store);
}
