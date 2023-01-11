package com.green.firstproject.repository.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.CouponInfoEntity;
import com.green.firstproject.entity.member.MemberCouponEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;

@Repository
public interface MemberCouponRepository extends JpaRepository<MemberCouponEntity, Long> {
     @Query("select mc from MemberCouponEntity mc join fetch mc.coupon join fetch mc.member where mc.member=:member")
     List<MemberCouponEntity> findByMember(@Param("member") MemberInfoEntity member);
     @Query("select mc from MemberCouponEntity mc join fetch mc.coupon join fetch mc.member where mc.member=:member and mc.coupon=:coupon")
     MemberCouponEntity findByMemberAndSeq(@Param("member") MemberInfoEntity member, @Param("coupon") CouponInfoEntity coupon);
}
