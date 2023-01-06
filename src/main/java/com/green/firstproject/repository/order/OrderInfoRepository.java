package com.green.firstproject.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{
     OrderInfoEntity findByOiSeqAndMember(Long seq, MemberInfoEntity member);
     List<OrderInfoEntity> findByMember(MemberInfoEntity member);
}
