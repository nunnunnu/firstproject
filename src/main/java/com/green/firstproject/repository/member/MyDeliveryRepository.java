package com.green.firstproject.repository.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.member.MyDeliveryEntity;
@Repository
public interface MyDeliveryRepository extends JpaRepository<MyDeliveryEntity, Long>{
    MyDeliveryEntity findByMdAddressAndMdDetailAddress(String address, String detailAddress);
    @Query("select m from MyDeliveryEntity m join fetch m.member where m.member=member")
    List<MyDeliveryEntity> findMember(@Param("member") MemberInfoEntity member);

    MyDeliveryEntity findByMdSeqAndMember(Long seq, MemberInfoEntity member);
    MyDeliveryEntity findByMemberAndMdBasic(MemberInfoEntity member, Integer num);
}
