package com.green.firstproject.service.order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.repository.master.PaymentInfoRepository;
import com.green.firstproject.vo.master.OrderPaymentChildVO;
import com.green.firstproject.vo.master.OrderPaymentVO;

@Service
public class PaymentService {
     @Autowired PaymentInfoRepository piRepo;

     public Map<String, Object> paymentSelect(Integer type){
          Map<String, Object> map = new LinkedHashMap<>();
          List<PaymentInfoEntity> payList = piRepo.findByPayType(type);
          if(payList.size()==0){
               map.put("status", false);
               map.put("message","번호를 잘못 입력하셨습니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }
          List<OrderPaymentChildVO> payChild = new ArrayList<>();
          for(PaymentInfoEntity p : payList){
               OrderPaymentChildVO payc = new OrderPaymentChildVO(p);
               payChild.add(payc);
          }
          OrderPaymentVO resultPay = new OrderPaymentVO(payChild, type);

          map.put("status", true);
          map.put("message","결제 페이지를 조회했습니다.");
          map.put("code", HttpStatus.OK);
          map.put("payList", resultPay);

          return map;
     }
}
