package com.green.firstproject.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.PaymentInfoEntity;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfoEntity, Long>{
     PaymentInfoEntity findByPaySeq(Long seq);

     List<PaymentInfoEntity> findByPayType(Integer payType);
}
