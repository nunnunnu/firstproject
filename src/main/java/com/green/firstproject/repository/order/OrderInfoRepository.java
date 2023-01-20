package com.green.firstproject.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{
     @Query("select o from OrderInfoEntity o join fetch o.pay where o.oiSeq=:seq and o.member=:member")
     OrderInfoEntity findByOiSeqAndMember(@Param("seq") Long seq, @Param("member") MemberInfoEntity member);

     List<OrderInfoEntity> findByMember(MemberInfoEntity member);

     @Query("select o from OrderInfoEntity o join fetch o.member m join fetch o.store s join fetch o.pay p left join fetch o.coupon c where m.miSeq=:seq order by o.oiOrderTime desc")
     List<OrderInfoEntity> findMember(@Param("seq") Long seq);
}
