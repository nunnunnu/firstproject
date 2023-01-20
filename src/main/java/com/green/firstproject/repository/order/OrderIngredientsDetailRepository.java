package com.green.firstproject.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;

@Repository
public interface OrderIngredientsDetailRepository extends JpaRepository<OrderIngredientsDetailEntity, Long> {
     @Query("select odi from OrderIngredientsDetailEntity odi join fetch odi.ingredient i where odi.orderdetail=:orderDetail")
     List<OrderIngredientsDetailEntity> findByOrderdetail(@Param("orderDetail") OrderDetailEntity orderdetail);
}
