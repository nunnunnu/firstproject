package com.green.firstproject.vo.master;

import com.green.firstproject.entity.master.PaymentInfoEntity;

import lombok.Data;

@Data
public class PaymentInfoVO {
     private String method;
     private String type;

     public PaymentInfoVO(PaymentInfoEntity pay){
          this.method = pay.getPayMethod();
          setPayType(pay.getPayType());
     }
     
     public void setPayType(Integer pay){
          if(pay==1){
               this.type="카드 결제";
          }else if(pay==2){
               this.type="만나서 결제";
          }
     }
}
