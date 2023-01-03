package com.green.firstproject.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.CouponInfoEntity;

@Repository
public interface CouponInfoRepository extends JpaRepository<CouponInfoEntity, Long>{
    
}
