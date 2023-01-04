package com.green.firstproject.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.service.order.OrderService;
import com.green.firstproject.service.order.cart.CartService;

import jakarta.servlet.http.HttpSession;

@RestController
public class OrderController {

     @Autowired CartService cartService;
     @Autowired MemberInfoReposiroty mReposiroty;
     @Autowired StoreInfoRepository sRepository;
     @Autowired OrderService orderService;

     @PutMapping("/cart")
     public ResponseEntity<Object> putCart(
          @RequestParam @Nullable Long menu,
          @RequestParam @Nullable Long event,
          @RequestParam @Nullable Long sideOpt,
          @RequestParam @Nullable Long drinkOpt,
          @RequestParam @Nullable Long drinkOpt2,
          HttpSession session,
          @RequestParam @Nullable Long... ingredients
     ){
          Map<String, Object> map = new LinkedHashMap<>();
          if(session.getAttribute("loginUser")==null){
               map.put("status", false);
               map.put("message", "로그인을 먼저 해주세요.");
               map.put("code", HttpStatus.ACCEPTED);
          }
          map  = cartService.addCart(menu, event, sideOpt, drinkOpt, drinkOpt2, ingredients);
          if(map.get("cart")==null){
               return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          }
          List<CartDetail> carts = (List<CartDetail>)session.getAttribute("cart");
          if(carts == null){
               carts = new ArrayList<>();
          }
          carts.add((CartDetail) map.get("cart"));
          session.setAttribute("cart", carts);
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          
     }
     //아직 재고 안뺌
     @PutMapping("/order")
     public ResponseEntity<Object> order(HttpSession session, @RequestParam Long paySeq){
          Map<String, Object> map = new LinkedHashMap<>();
          // if(session.getAttribute("loginUser")==null){
          //      map.put("status", false);
          //      map.put("message", "로그인을 먼저 해주세요.");
          //      map.put("code", HttpStatus.ACCEPTED);
          //      return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
          // } //로그인 기능 아직 안됨
          MemberInfoEntity member = mReposiroty.findAll().get(0);
          StoreInfoEntity store = sRepository.findAll().get(0);
          List<CartDetail> carts = (List<CartDetail>)session.getAttribute("cart");
          
          map = orderService.order(member, store, paySeq, carts);       
          
          return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
     }
}
