package com.green.firstproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.MemberCouponEntity;

@Repository
public interface MemberCouponRepository extends JpaRepository<MemberCouponEntity, Long> {
    
}
