package com.green.firstproject.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
     @Query("select od from OrderDetailEntity od left join fetch od.odBiseq left join fetch od.odEiSeq left join fetch od.odLsotSeq left join fetch od.odLdotSeq left join fetch od.odLdot2Seq where od.odOiseq=:order")
     List<OrderDetailEntity> findByOdOiseq(@Param("order") OrderInfoEntity odOiseq);
}
