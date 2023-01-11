package com.green.firstproject.vo.master;

import com.green.firstproject.entity.master.PaymentInfoEntity;

import lombok.Data;
@Data
public class OrderPaymentChildVO {
     private Long paySeq;
     private String payMethod;

     public OrderPaymentChildVO(PaymentInfoEntity pay){
          this.paySeq=pay.getPaySeq();
          this.payMethod = pay.getPayMethod();
     }
}
