package com.green.firstproject.service.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.repository.master.PaymentInfoRepository;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.repository.order.OrderDetailRepository;
import com.green.firstproject.repository.order.OrderInfoRepository;
import com.green.firstproject.repository.order.OrderIngredientsDetailRepository;
import com.green.firstproject.repository.stock.BurgerStockRepository;
import com.green.firstproject.vo.order.OrderDetailVO;
import com.green.firstproject.vo.order.OrderVO;

@Service
public class OrderService {
     
     @Autowired MemberInfoReposiroty mRepo;
     @Autowired StoreInfoRepository siRepo;
     @Autowired MenuInfoRepository menuRepo;
     @Autowired EventInfoRepository eRepo;
     @Autowired SideOptionRepository soRepo;
     @Autowired DrinkOptionRepository diRepo;
     @Autowired IngredientsInfoRepository iiRepo;
     @Autowired OrderDetailRepository odRepo;
     @Autowired OrderInfoRepository oiRepository;
     @Autowired PaymentInfoRepository piRepo;
     @Autowired OrderIngredientsDetailRepository oidRepo;
     @Autowired BurgerStockRepository bsRepo;

     public Map<String, Object> order(MemberInfoEntity member, StoreInfoEntity store,
     Long paySeq ,List<CartDetail> carts){
          Map<String, Object> resultMap = new LinkedHashMap<>();
          if(carts==null){
               resultMap.put("status", false);
               resultMap.put("message", "아직 장바구니에 아무것도 추가되지않았습니다. 장바구니에 메뉴를 먼저 담아주세요.");
               resultMap.put("code", HttpStatus.BAD_REQUEST);
               return resultMap;
          }
          PaymentInfoEntity pay = piRepo.findByPaySeq(paySeq);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null);
          
          oiRepository.save(order);
          // order = oiRepository.findByOiSeq(orderSeq);
          System.out.println(order);
          OrderVO orderVo = new OrderVO(order);
          List<Object> list = new ArrayList<>();
          for(CartDetail c : carts){
               OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity();
               OrderDetailEntity orderDetail = new OrderDetailEntity(c);
               orderDetail.setOdOiseq(order);
               odRepo.save(orderDetail);
               BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, orderDetail.getOdBiseq().getBurger());
               int bStock = burgerStock.getBsStock() - orderDetail.getOdCount();
               burgerStock.setBsStock(bStock);
               
               System.out.println(burgerStock.getBsStock());
               
               for(IngredientsInfoEntity i : c.getIngredient()){
                    orderIngredient.setIngredient(i);
                    orderIngredient.setOrderdetail(orderDetail);
                    oidRepo.save(orderIngredient);
               }
               OrderDetailVO oDetailVO = new OrderDetailVO(orderDetail);
               orderVo.setTotalPrice(oDetailVO);
               
               list.add(oDetailVO);
          }
          
          resultMap.put("order", orderVo);
          resultMap.put("status", true);
          resultMap.put("message", "주문이 완료되었습니다.");
          resultMap.put("code", HttpStatus.ACCEPTED);
          resultMap.put("detail", list);
          return resultMap;
     }


}
