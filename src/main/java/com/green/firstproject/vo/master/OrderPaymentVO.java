package com.green.firstproject.vo.master;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderPaymentVO {
     private String payType;
     private List<OrderPaymentChildVO> pays;

     public void payTypeSetting(Integer type){
          if(type==1){
               this.payType="카드 결제";
          }else if(type==2){
               this.payType="만나서 결제";
          }
     }

     public OrderPaymentVO(List<OrderPaymentChildVO> p, Integer type){
          payTypeSetting(type);
          pays = new ArrayList<>();
          pays.addAll(p);
     }
}
