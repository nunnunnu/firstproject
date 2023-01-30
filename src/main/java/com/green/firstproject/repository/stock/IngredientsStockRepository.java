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
import com.green.firstproject.vo.stock.BurgerStockVO;

@Repository
public interface IngredientsStockRepository extends JpaRepository<IngredientsStockEntity, Long>{
     IngredientsStockEntity findByStoreAndIngredient(StoreInfoEntity store, IngredientsInfoEntity ingredient);

     @Query("select is from IngredientsStockEntity is join fetch is.ingredient i where store=:store and i.iiSeq in (:ingSeqs)")
     List<IngredientsStockEntity> findStoreAndIngredient(@Param("store") StoreInfoEntity store, @Param("ingSeqs") Collection<Long> seqs);

     @Query(value="select a.ii_seq as seq , a.ii_name as name ,if(ifnull(c.is_stock,0)>=1,'판매 가능', '판매 불가능') as stock from (select * from ingredients_stock b where b.is_si_seq = :store ) c right outer join ingredients_info a on a.ii_seq = c.is_ii_seq"
          ,nativeQuery = true)
     List<BurgerStockVO> stockAll(@Param("store") Long store);
}
