package com.green.firstproject.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.stock.EventStockEntity;
import com.green.firstproject.vo.stock.BurgerStockVO;

@Repository
public interface EventStockRepository extends JpaRepository<EventStockEntity,Long>{
     EventStockEntity findByStoreAndEvent(StoreInfoEntity store, EventInfoEntity event);

     @Query(value="select a.ei_seq as seq , a.ei_name as name ,if(ifnull(c.es_stock,0)>=1,'판매 가능', '판매 불가능') as stock from (select * from event_stock b where b.es_si_seq = :store ) c right outer join event_info a on a.ei_seq = c.es_ei_seq"
          ,nativeQuery = true)
     List<BurgerStockVO> stockAll(@Param("store") Long store);
}
