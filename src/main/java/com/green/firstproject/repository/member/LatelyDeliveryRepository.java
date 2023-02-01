package com.green.firstproject.repository.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.LatelyDeliveryEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
@Repository
public interface LatelyDeliveryRepository extends JpaRepository<LatelyDeliveryEntity, Long>{
     LatelyDeliveryEntity findByLdAddressAndLdDetailAddress(String address, String detailAddress);
     @Query("select l from LatelyDeliveryEntity l join fetch l.member where l.member=:member")
     List<LatelyDeliveryEntity> findMember(@Param("member") MemberInfoEntity member);

     List<LatelyDeliveryEntity> findByMember(@Param("member") MemberInfoEntity member);

}
