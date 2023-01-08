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
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.entity.stock.DogStockEntity;
import com.green.firstproject.entity.stock.DrinkStockEntity;
import com.green.firstproject.entity.stock.EventStockEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;
import com.green.firstproject.entity.stock.SideStockEntity;
import com.green.firstproject.repository.master.PaymentInfoRepository;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
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
import com.green.firstproject.repository.stock.EventStockRepository;
import com.green.firstproject.repository.stock.IngredientsStockRepository;
import com.green.firstproject.repository.stock.SideStockRepository;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.menu.IngredientVo;
import com.green.firstproject.vo.order.OrderDetailVO;
import com.green.firstproject.vo.order.OrderIngredientsVO;
import com.green.firstproject.vo.order.OrderVO;

import io.micrometer.common.lang.Nullable;

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
     @Autowired EventStockRepository esRepo;
     @Autowired BurgerInfoRepository biRepo;

     public Map<String, Object> order(MemberInfoEntity member, StoreInfoEntity store,
          Long paySeq ,List<CartDetail> c, 
          @Nullable Long... seq //주문할 카트번호를 가변인자로 받음
     ){   
          Map<String, Object> resultMap = new LinkedHashMap<>();
          if(c==null || c.size()==0){ 
               resultMap.put("status", false);
               resultMap.put("message", "아직 카트에 아무것도 추가되지않았습니다. 장바구니에 메뉴를 먼저 담아주세요.");
               resultMap.put("code", HttpStatus.BAD_REQUEST);
               return resultMap;
          }
          List<CartDetail> carts = new ArrayList<>();
          List<CartDetail> notOrders = new ArrayList<>();
          if(seq==null || seq.length==0 ){ //주문할 메뉴를 선택하지 않았다면 모두 주문으로 처리
               carts = c;
          }else{
               for(CartDetail cart : c){
                    Boolean check = false;
                    for(Long no : seq){
                         if(no==cart.getSeq()){
                              carts.add(cart);
                              check=true;
                         }
                    }
                    if(!check){
                         notOrders.add(cart); //카트에서 주문하지 않은 메뉴는 지우지않고 남겨주기위해 list 생성
                    }
               }
          }
          if(c.size()==notOrders.size()){ //주문한 카트번호와 일치하는 카트가 없었다면 에러처리
               resultMap.put("status", false);
               resultMap.put("message", "카트 번호를 잘못선택하셨습니다.");
               resultMap.put("code", HttpStatus.BAD_REQUEST);
               resultMap.put("notOrders", notOrders);
               return resultMap;
          }
          if(!stockCheck(carts, store)){ //재고가 부족하다면
               resultMap.put("status", false);
               resultMap.put("message", "품절중인 메뉴가 포함되어있습니다.");
               resultMap.put("code", HttpStatus.BAD_REQUEST);
               resultMap.put("notOrders", notOrders);
               return resultMap;
          }
          PaymentInfoEntity pay = piRepo.findByPaySeq(paySeq);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null); //쿠폰 기능 아직 구현 못함
          
          oiRepository.save(order);
          OrderVO orderVo = new OrderVO(order);
          List<Object> list = new ArrayList<>();
          for(CartDetail ca : carts){
               OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity();
               OrderDetailEntity orderDetail = new OrderDetailEntity(ca);
               orderDetail.setOdOiseq(order);
               if(ca.getMenu().getBurger()!=null){
                    BurgerInfoEntity burger = biRepo.findByBiSeq(ca.getMenu().getBurger().getBiSeq());
                    burger.upSales(); //판매량 증가
                    biRepo.save(burger);
               }
               
               //재고 감소 기능
               discountStock(store, orderDetail);
               
               odRepo.save(orderDetail);
               OrderDetailVO oDetailVO = new OrderDetailVO(orderDetail);
               oDetailVO.setDetailPrice(ca);
               List<OrderIngredientsVO> ingList = new ArrayList<>();
               for(IngredientVo ing : ca.getIngredient()){
                    IngredientsInfoEntity i = iiRepo.findByIiSeq(ing.getIngredirentSeq());
                    orderIngredient.setIngredient(i);
                    orderIngredient.setOrderdetail(orderDetail);
                    discountIngredientStock(store, i);
                    oidRepo.save(orderIngredient);
                    ingList.add(new OrderIngredientsVO(orderIngredient));
               }
               orderVo.setTotalPrice(carts);
               oDetailVO.addOrderIngredients(ingList);
               list.add(oDetailVO);
          }
          
          resultMap.put("order", orderVo);
          resultMap.put("status", true);
          resultMap.put("message", "주문이 완료되었습니다.");
          resultMap.put("code", HttpStatus.ACCEPTED);
          resultMap.put("detail", list);
          resultMap.put("notOrders", notOrders);
          return resultMap;
     }

     //기본메뉴 재고감소
     public void discountStock(StoreInfoEntity store, OrderDetailEntity orderDetail){   
          if(orderDetail.getOdBiseq().getBurger()!=null){
               BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, orderDetail.getOdBiseq().getBurger());
               int bStock = burgerStock.getBsStock() - orderDetail.getOdCount();
               burgerStock.setBsStock(bStock);
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
          if(orderDetail.getOdEiSeq()!=null){
               EventStockEntity event = esRepo.findByStoreAndEvent(store, orderDetail.getOdEiSeq());
               int eventStock = event.getEsStock() - orderDetail.getOdCount();
               event.setEsStock(eventStock);
          }
     }
     //재료 재고 감소
     public void discountIngredientStock(StoreInfoEntity store, IngredientsInfoEntity ingredirent){
          IngredientsStockEntity ing = isRepo.findByStoreAndIngredient(store, ingredirent);
          int ingStock = ing.getIsStock()-1;
          ing.setIsStock(ingStock);
     }
     // 매장 재고 검사
     public Boolean stockCheck(List<CartDetail> carts, StoreInfoEntity store){
          for(CartDetail c : carts){
               BurgerInfoEntity burger = c.getMenu().getBurger();
               DogInfoEntity dog = c.getMenu().getDog();
               DrinkInfoEntity drink = c.getMenu().getDrink();
               SideInfoEntity side = c.getMenu().getSide();
               EventInfoEntity event = c.getEvent();
               if(burger!=null){
                    BurgerStockEntity bs = bsRepo.findByStoreAndBurger(store, burger);
                    if(bs.getBsStock()<c.getOdCount()){
                         return false; //재고없음
                    }
               }
               if(dog!=null){
                    DogStockEntity dogstock = dogsRepo.findByStoreAndDog(store, dog);
                    if(dogstock.getDogsStock()<c.getOdCount()){
                         return false; //재고없음
                    }
               }
               if(drink!=null){
                    DrinkStockEntity ds = dsRepo.findByStoreAndDrink(store, drink);
                    if(ds.getDsStock()<c.getOdCount()){
                         return false; //재고없음
                    }
               }
               if(side!=null){
                    SideStockEntity ss = ssRepo.findByStoreAndSide(store, side);
                    if(ss.getSsStock()<c.getOdCount()){
                         return false; //재고없음
                    }
               }
               if(event!=null){
                    EventStockEntity es = esRepo.findByStoreAndEvent(store, event);
                    if(es.getEsStock()<c.getOdCount()){
                         return false; //재고없음
                    }
               }
               for(IngredientVo i : c.getIngredient()){
                    IngredientsInfoEntity ingredientsInfoEntity = iiRepo.findByIiSeq(i.getIngredirentSeq());
                    IngredientsStockEntity ing = isRepo.findByStoreAndIngredient(store, ingredientsInfoEntity);
                    if(ing.getIsStock()<c.getOdCount()){
                         return false;
                    }
               }
          }
          return true;
     }

     //주문 취소
     public Map<String, Object> orderCancel(Long seq, LoginUserVO login){
          Map<String, Object> map = new LinkedHashMap<>();
          MemberInfoEntity member = mRepo.findByMiEmail(login.getEmail());
          OrderInfoEntity order = oiRepository.findByOiSeqAndMember(seq, member);
          if(order==null){
               map.put("status", false);
               map.put("message", "일치하는 주문이 없습니다. 본인이 주문한 주문이 아니거나 주문번호가 잘못되었습니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }
          if(order.getOiStatus()!=1 || order.getOiStatus()!=2){
               map.put("status", false);
               map.put("message", "이미 배송중이거나 배송이 완료된 주문은 취소할 수 없습니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }
          order.setOiStatus(5);
          oiRepository.save(order);
          map.put("status", true);
          map.put("message", "주문이 취소되었습니다.");
          map.put("code", HttpStatus.ACCEPTED);
          return map;
     }
     
     //주문 리스트 조회
     public Map<String, Object> showMyOrder(LoginUserVO login){
          Map<String, Object> map = new LinkedHashMap<>();
          /* 귀찮아서 주석함 나중에 풀어야함 */
          // MemberInfoEntity member = mRepo.findByMiEmail(login.getEmail());
          MemberInfoEntity member = mRepo.findAll().get(0);
          
          List<OrderInfoEntity> orders = oiRepository.findMember(member.getMiSeq());
          
          if(orders.size()==0){
               map.put("status", false);
               map.put("message", "주문내역이 존재하지 않습니다.");
               map.put("code", HttpStatus.ACCEPTED);
               return map;
          }
          List<OrderVO> resultOrder = new ArrayList<>();
          for(OrderInfoEntity o : orders){
               OrderVO order = new OrderVO(o);
               List<OrderDetailEntity> orderDetails = odRepo.findByOdOiseq(o);
               List<OrderDetailVO> orderDetailVo = new ArrayList<>();
               if(orderDetails!=null){
                    for(OrderDetailEntity od : orderDetails){
                         OrderDetailVO orderDe = new OrderDetailVO(od);
                         orderDe.addPrice(od);
                         orderDetailVo.add(orderDe);
                         
                         order.setOrderPrice(od);
                         List<OrderIngredientsDetailEntity> ingredients = oidRepo.findByOrderdetail(od);
                         if(ingredients!=null){
                              int count=0;
                              for(OrderIngredientsDetailEntity i : ingredients){
                                   if(i.getIngredient().getIiPrice()==0){
                                        if(count>1){
                                             order.addCheckIngredientPrice();
                                        }
                                        count++;
                                   }else{
                                        order.addIngredientPrice(i);
                                   }
                                   
                              }
                         }
                    }   
               }
               order.addOrderDetail(orderDetailVo);
               resultOrder.add(order);
          }
          
          map.put("status", true);
          map.put("message", "주문 내역을 조회했습니다.");
          map.put("code", HttpStatus.ACCEPTED);
          map.put("list", resultOrder);
          return map;
     }
}
