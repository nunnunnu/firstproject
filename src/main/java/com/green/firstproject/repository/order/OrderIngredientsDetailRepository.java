package com.green.firstproject.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;

@Repository
public interface OrderIngredientsDetailRepository extends JpaRepository<OrderIngredientsDetailEntity, Long> {
     List<OrderIngredientsDetailEntity> findByOrderdetail(OrderDetailEntity orderdetail);
}
