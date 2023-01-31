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
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.service.order.OrderService;
import com.green.firstproject.service.order.PaymentService;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.order.OrderFormVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController {

     // @Autowired CartService cartService;
     @Autowired MemberInfoReposiroty mReposiroty;
     @Autowired StoreInfoRepository sRepository;
     @Autowired OrderService orderService;
     @Autowired PaymentService payService;

     @PutMapping("")
     public ResponseEntity<Object> order(HttpSession session, @RequestBody OrderFormVO oVo
     ){
          Map<String, Object> map = new LinkedHashMap<>();
          // if(session.getAttribute("loginUser")==null){
               //      map.put("status", false);
               //      map.put("message", "로그인을 먼저 해주세요.");
               //      map.put("code", HttpStatus.ACCEPTED);
               //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //로그인 기능 아직 안됨
          // String address = (String)session.getAttribute("address");
          // String detailAddress = (String)session.getAttribute("address");
          // String address = "대구광역시 중구 109-2";
          // String detailAddress = "그린컴퓨터학원 5층";
          MemberInfoEntity member = mReposiroty.findAll().get(0); //로그인회원 임시 고정
          StoreInfoEntity store = sRepository.findBySiSeq(oVo.getStore()); //선택 매장 임시 고정
          
          if(LocalTime.now().isBefore(store.getSiOpenTime()) || LocalTime.now().isAfter(store.getSiCloseTime())){
               map.put("status", false);
               map.put("message", "현재 선택된 매장은 영업시간이 아닙니다. "+store.getSiOpenTime()+"~"+store.getSiCloseTime()+"사이에 주문해주세요.");
               map.put("code", HttpStatus.ACCEPTED);
               return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          }
          
          map = orderService.order(member, store, oVo.getPay(), oVo.getCart(), oVo.getMessage(), oVo.getCouponSeq(), oVo.getAddress(), oVo.getDetailAddress());  

          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     
     @PatchMapping("/cancel/{seq}")
     public ResponseEntity<Object> orderCancel(@PathVariable Long seq, HttpSession session){
          Map<String, Object> map = new LinkedHashMap<>();
          
          // LoginUserVO loginUser = (LoginUserVO) session.getAttribute("loginUser");
          // if(loginUser==null){
          //      map.put("status", false);
          //      map.put("message", "로그인 후 사용가능한 기능입니다.");
          //      map.put("code", HttpStatus.BAD_GATEWAY);
          
          //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //귀찮아서 주석처리함
          LoginUserVO loginUser = new LoginUserVO(mReposiroty.findAll().get(0));
          map = orderService.orderCancel(seq, loginUser);
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     
     @GetMapping("/list")
     public ResponseEntity<Object> showMyOrderList(HttpSession session){
          Map<String, Object> map = new LinkedHashMap<>();
          LoginUserVO login = (LoginUserVO) session.getAttribute("loginUser");
          // if(loginUser==null){
          //      map.put("status", false);
          //      map.put("message", "로그인 후 사용가능한 기능입니다.");
          //      map.put("code", HttpStatus.BAD_GATEWAY);
               
          //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //귀찮아서 주석처리함
          map=orderService.showMyOrder(login);
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     @GetMapping("/detail/{seq}")
     public ResponseEntity<Object> showDetailMyOrder(@PathVariable("seq") Long seq, HttpSession session){
          Map<String, Object> map = new LinkedHashMap<>();
          LoginUserVO login = (LoginUserVO) session.getAttribute("loginUser");
          // if(loginUser==null){
               //      map.put("status", false);
               //      map.put("message", "로그인 후 사용가능한 기능입니다.");
               //      map.put("code", HttpStatus.BAD_GATEWAY);
               
               //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //귀찮아서 주석처리함
          map = orderService.showDetailOrderList(login, seq);
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     
     @GetMapping("payment/{type}")
     public ResponseEntity<Object> paymentPage(HttpSession session, @PathVariable Integer type){
          Map<String, Object> map = new LinkedHashMap<>();
          LoginUserVO login = (LoginUserVO) session.getAttribute("loginUser");
          
          // if(loginUser==null){
               //      map.put("status", false);
               //      map.put("message", "로그인 후 사용가능한 기능입니다.");
               //      map.put("code", HttpStatus.BAD_GATEWAY);
               
               //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //귀찮아서 주석처리함
          map = payService.paymentSelect(type);
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     @GetMapping("/info")
     public ResponseEntity<Object> orderPayment(HttpSession session, @RequestBody OrderFormVO oVo){
          Map<String, Object> map = new LinkedHashMap<>();
          LoginUserVO login = (LoginUserVO) session.getAttribute("loginUser");
          // if(loginUser==null){
               //      map.put("status", false);
               //      map.put("message", "로그인 후 사용가능한 기능입니다.");
               //      map.put("code", HttpStatus.BAD_GATEWAY);
               
               //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //귀찮아서 주석처리함
          StoreInfoEntity store = sRepository.findAll().get(0); //매장 고정. 이후 변경 필요
          // List<CartDetail> carts = (List<CartDetail>)session.getAttribute("cart");
          map = orderService.orderPage(login, store, oVo.getCart());
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
}
