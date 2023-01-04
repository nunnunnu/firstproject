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
import com.green.firstproject.entity.stock.DogStockEntity;
import com.green.firstproject.entity.stock.DrinkStockEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;
import com.green.firstproject.entity.stock.SideStockEntity;
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
import com.green.firstproject.repository.stock.DogStockRepository;
import com.green.firstproject.repository.stock.DrinkStockRepository;
import com.green.firstproject.repository.stock.IngredientsStockRepository;
import com.green.firstproject.repository.stock.SideStockRepository;
import com.green.firstproject.vo.order.OrderDetailVO;
import com.green.firstproject.vo.order.OrderIngredientsVO;
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
     @Autowired DogStockRepository dogsRepo;
     @Autowired DrinkStockRepository dsRepo;
     @Autowired SideStockRepository ssRepo;
     @Autowired IngredientsStockRepository isRepo;

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
               System.out.println(c);
               OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity();
               OrderDetailEntity orderDetail = new OrderDetailEntity(c);
               System.out.println(orderDetail);
               orderDetail.setOdOiseq(order);
               
               //재고 감소 기능
               discountStock(store, orderDetail);
               
               odRepo.save(orderDetail);

               OrderDetailVO oDetailVO = new OrderDetailVO(orderDetail);
               List<OrderIngredientsVO> ingList = new ArrayList<>();
               for(IngredientsInfoEntity i : c.getIngredient()){
                    orderIngredient.setIngredient(i);
                    orderIngredient.setOrderdetail(orderDetail);
                    discountIngredientStock(store, i);
                    oidRepo.save(orderIngredient);
                    ingList.add(new OrderIngredientsVO(orderIngredient));
               }
               orderVo.setTotalPrice(oDetailVO);
               oDetailVO.addOrderIngredients(ingList);
               list.add(oDetailVO);
          }
          
          resultMap.put("order", orderVo);
          resultMap.put("status", true);
          resultMap.put("message", "주문이 완료되었습니다.");
          resultMap.put("code", HttpStatus.ACCEPTED);
          resultMap.put("detail", list);
          return resultMap;
     }

     //기본메뉴 재고감소
     public void discountStock(StoreInfoEntity store, OrderDetailEntity orderDetail){   
          if(orderDetail.getOdBiseq().getBurger()!=null){
               BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, orderDetail.getOdBiseq().getBurger());
               System.out.println(burgerStock);
               int bStock = burgerStock.getBsStock() - orderDetail.getOdCount();
               System.out.println(bStock);
               burgerStock.setBsStock(bStock);
               // System.out.println(burgerStock.getBsStock());
          }
          if(orderDetail.getOdBiseq().getDog()!=null){
               DogStockEntity dogStock = dogsRepo.findByStoreAndDog(store, orderDetail.getOdBiseq().getDog());
               int dogSto = dogStock.getDogsStock() - orderDetail.getOdCount();
               dogStock.setDogsStock(dogSto);
          }
          if(orderDetail.getOdBiseq().getDrink()!=null){
               DrinkStockEntity drinkStock = dsRepo.findByStoreAndDrink(store, orderDetail.getOdBiseq().getDrink());
               int dStock = drinkStock.getDsStock() - orderDetail.getOdCount();
               drinkStock.setDsStock(dStock);
          }
          if(orderDetail.getOdBiseq().getSide()!=null){
               SideStockEntity side = ssRepo.findByStoreAndSide(store, orderDetail.getOdBiseq().getSide());
               int sideStock = side.getSsStock() - orderDetail.getOdCount();
               side.setSsStock(sideStock);
          }

     }
     //재료 재고 감소
     public void discountIngredientStock(StoreInfoEntity store, IngredientsInfoEntity ingredirent){
          IngredientsStockEntity ing = isRepo.findByStoreAndIngredient(store, ingredirent);
          int ingStock = ing.getIsStock()-1;
          ing.setIsStock(ingStock);
     }
     
}
