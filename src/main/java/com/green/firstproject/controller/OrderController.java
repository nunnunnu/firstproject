package com.green.firstproject.controller;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.service.order.OrderService;
import com.green.firstproject.service.order.PaymentService;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.order.OrderFormVO;

@RestController
@RequestMapping("/order")
public class OrderController {

     @Autowired MemberInfoReposiroty mReposiroty;
     @Autowired StoreInfoRepository sRepository;
     @Autowired OrderService orderService;
     @Autowired PaymentService payService;

     @PutMapping("")
     public ResponseEntity<Object> order(@RequestBody OrderFormVO oVo
     ){
          Map<String, Object> map = new LinkedHashMap<>();
          MemberInfoEntity member = mReposiroty.findByMiSeq(oVo.getMember());
          StoreInfoEntity store = sRepository.findBySiSeq(oVo.getStore()); 
          
          if(LocalTime.now().isBefore(store.getSiOpenTime()) || LocalTime.now().isAfter(store.getSiCloseTime())){
               map.put("status", false);
               map.put("message", "현재 선택된 매장은 영업시간이 아닙니다. "+store.getSiOpenTime()+"~"+store.getSiCloseTime()+"사이에 주문해주세요.");
               map.put("code", HttpStatus.ACCEPTED);
               return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          }
          
          map = orderService.order(member, store, oVo.getPay(), oVo.getCart(), oVo.getMessage(), oVo.getCouponSeq(), oVo.getAddress(), oVo.getDetailAddress());  

          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     
     @PatchMapping("/cancel/{seq}/{member}")
     public ResponseEntity<Object> orderCancel(@PathVariable Long seq, @PathVariable Long member){
          Map<String, Object> map = new LinkedHashMap<>();
          
          LoginUserVO loginUser = new LoginUserVO(mReposiroty.findByMiSeq(member));
          map = orderService.orderCancel(seq, loginUser);
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     
     @GetMapping("/list/{member}")
     public ResponseEntity<Object> showMyOrderList(@PathVariable Long member){
          Map<String, Object> map = new LinkedHashMap<>();
          
          map=orderService.showMyOrder(mReposiroty.findByMiSeq(member));
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     @GetMapping("/detail/{seq}/{member}")
     public ResponseEntity<Object> showDetailMyOrder(@PathVariable("seq") Long seq, @PathVariable Long member){
          Map<String, Object> map = new LinkedHashMap<>();
          
          map = orderService.showDetailOrderList(mReposiroty.findByMiSeq(member), seq);
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     
     @GetMapping("payment/{type}")
     public ResponseEntity<Object> paymentPage(@PathVariable Integer type){
          Map<String, Object> map = new LinkedHashMap<>();
          
          map = payService.paymentSelect(type);
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     @GetMapping("/info")
     public ResponseEntity<Object> orderPayment(@RequestBody OrderFormVO oVo){
          Map<String, Object> map = new LinkedHashMap<>();
          StoreInfoEntity store = sRepository.findAll().get(0); //매장 고정. 이후 변경 필요
          map = orderService.orderPage(store, oVo.getCart());
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
}
