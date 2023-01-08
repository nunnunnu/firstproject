package com.green.firstproject.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.service.order.OrderService;
import com.green.firstproject.service.order.cart.CartService;
import com.green.firstproject.vo.member.LoginUserVO;

import jakarta.servlet.http.HttpSession;

@RestController
public class OrderController {

     @Autowired CartService cartService;
     @Autowired MemberInfoReposiroty mReposiroty;
     @Autowired StoreInfoRepository sRepository;
     @Autowired OrderService orderService;

     @PutMapping("/order")
     public ResponseEntity<Object> order(HttpSession session, @RequestParam Long paySeq,
          @RequestParam @Nullable Long...cartSeq
     ){
          Map<String, Object> map = new LinkedHashMap<>();
          // if(session.getAttribute("loginUser")==null){
               //      map.put("status", false);
               //      map.put("message", "로그인을 먼저 해주세요.");
               //      map.put("code", HttpStatus.ACCEPTED);
               //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
               // } //로그인 기능 아직 안됨
          MemberInfoEntity member = mReposiroty.findAll().get(0); //로그인회원 임시 고정
          StoreInfoEntity store = sRepository.findAll().get(0); //선택 매장 임시 고정
          
          if(LocalTime.now().isBefore(store.getSiOpenTiem()) || LocalTime.now().isAfter(store.getSiCloseTime())){
               map.put("status", false);
               map.put("message", "현재 선택된 매장은 영업시간이 아닙니다. "+store.getSiOpenTiem()+"~"+store.getSiCloseTime()+"사이에 주문해주세요.");
               map.put("code", HttpStatus.ACCEPTED);
               return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          }
          List<CartDetail> carts = (List<CartDetail>)session.getAttribute("cart");
          
          map = orderService.order(member, store, paySeq, carts, cartSeq);  
          session.setAttribute("cart", map.get("notOrders"));

          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
     
     @GetMapping("/order/cancel")
     public ResponseEntity<Object> orderCancel(@RequestParam Long seq, HttpSession session){
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
     
     @GetMapping("/order/list")
     public ResponseEntity<Object> showMyOrderList(HttpSession session){
          Map<String, Object> map = new LinkedHashMap<>();
          // LoginUserVO loginUser = (LoginUserVO) session.getAttribute("loginUser");
          // if(loginUser==null){
          //      map.put("status", false);
          //      map.put("message", "로그인 후 사용가능한 기능입니다.");
          //      map.put("code", HttpStatus.BAD_GATEWAY);
               
          //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //귀찮아서 주석처리함
          LoginUserVO login = (LoginUserVO) session.getAttribute("loginUser");
          map=orderService.showMyOrder(login);
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
}
