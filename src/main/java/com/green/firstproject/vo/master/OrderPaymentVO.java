package com.green.firstproject.vo.master;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderPaymentVO {
     private String type;
     private List<OrderPaymentChildVO> pays;

     public void payTypeSetting(Integer type){
          if(type==1){
               this.type="카드 결제";
          }else if(type==2){
               this.type="만나서 결제";
          }
     }

     public OrderPaymentVO(List<OrderPaymentChildVO> p, Integer type){
          payTypeSetting(type);
          pays = new ArrayList<>();
          pays.addAll(p);
     }
}
