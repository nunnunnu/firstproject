package com.green.firstproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.member.LatelyDeliveryEntity;
@Repository
public interface LatelyDeliveryRepository extends JpaRepository<LatelyDeliveryEntity, Long>{
     LatelyDeliveryEntity findByLdAddressAndLdDetailAddress(String address, String detailAddress);
}
