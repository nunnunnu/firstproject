package com.green.firstproject.vo.master;

import com.green.firstproject.entity.master.PaymentInfoEntity;

import lombok.Data;
@Data
public class OrderPaymentChildVO {
     private Long seq;
     private String method;

     public OrderPaymentChildVO(PaymentInfoEntity pay){
          this.seq=pay.getPaySeq();
          this.method = pay.getPayMethod();
     }
}
