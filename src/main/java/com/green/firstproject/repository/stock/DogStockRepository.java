package com.green.firstproject.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.stock.DogStockEntity;
import com.green.firstproject.vo.stock.BurgerStockVO;

@Repository
public interface DogStockRepository extends JpaRepository<DogStockEntity,Long>{
     DogStockEntity findByStoreAndDog(StoreInfoEntity store, DogInfoEntity dog);

     @Query(value="select a.dog_seq as seq , a.dog_name as name ,if(ifnull(c.dogs_stock,0)>=1,'판매 가능', '판매 불가능') as stock from (select * from dog_stock b where b.dogs_si_seq = :store ) c right outer join dog_info a on a.dog_seq = c.dogs_dog_seq"
          ,nativeQuery = true)
     List<BurgerStockVO> stockAll(@Param("store") Long store);
}
