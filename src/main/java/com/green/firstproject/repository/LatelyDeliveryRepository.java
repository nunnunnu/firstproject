package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.LatelyDeliveryEntity;
@Repository
public interface LatelyDeliveryRepository extends JpaRepository<LatelyDeliveryEntity, Long>{
     
}
