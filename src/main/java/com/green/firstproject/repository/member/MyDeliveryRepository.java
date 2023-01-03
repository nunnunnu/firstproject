package com.green.firstproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.MyDeliveryEntity;
@Repository
public interface MyDeliveryRepository extends JpaRepository<MyDeliveryEntity, Long>{
     
}
