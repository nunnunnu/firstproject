package com.green.firstproject.vo.master;

import com.green.firstproject.entity.master.PaymentInfoEntity;

import lombok.Data;

@Data
public class PaymentInfoVO {
     private String payMethod;
     private String payType;

     public PaymentInfoVO(PaymentInfoEntity pay){
          this.payMethod = pay.getPayMethod();
          setPayType(pay.getPayType());
     }
     
     public void setPayType(Integer pay){
          if(pay==1){
               this.payType="카드 결제";
          }else if(pay==2){
               this.payType="만나서 결제";
          }
     }
}
