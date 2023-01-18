package com.green.firstproject.service.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.CouponInfoEntity;
import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.LatelyDeliveryEntity;
import com.green.firstproject.entity.member.MemberCouponEntity;
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
import com.green.firstproject.entity.order.cart.CartVo;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.entity.stock.DogStockEntity;
import com.green.firstproject.entity.stock.DrinkStockEntity;
import com.green.firstproject.entity.stock.EventStockEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;
import com.green.firstproject.entity.stock.SideStockEntity;
import com.green.firstproject.repository.master.CouponInfoRepository;
import com.green.firstproject.repository.master.PaymentInfoRepository;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.LatelyDeliveryRepository;
import com.green.firstproject.repository.member.MemberCouponRepository;
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
import com.green.firstproject.vo.master.CouponVO;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.menu.IngredientVo;
import com.green.firstproject.vo.order.MyOrderDetailVO;
import com.green.firstproject.vo.order.MyOrderViewVO;
import com.green.firstproject.vo.order.OrderDeliveryVO;
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
     @Autowired MemberCouponRepository mcRepo;
     @Autowired CouponInfoRepository cRepo;
     @Autowired LatelyDeliveryRepository ldRepo;

     public Map<String, Object> order(MemberInfoEntity member, StoreInfoEntity store,
          Long paySeq ,List<CartDetail> c, 
          @Nullable String message, @Nullable Set<Long> seq,
          @Nullable Long couponSeq,
          String address, String detailAddress
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
          if(seq==null || seq.size()==0 ){ //주문할 메뉴를 선택하지 않았다면 모두 주문으로 처리
               carts = c;
          }else{
               for(CartDetail cart : c){
                    Boolean check = false;
                    for(Long no : seq){
                         if(no==cart.getCartSeq()){
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
               resultMap.put("notOrders", c);
               return resultMap;
          }
          PaymentInfoEntity pay = piRepo.findByPaySeq(paySeq);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null, message, address+" "+detailAddress); 
          
          if(couponSeq!=null){
               CouponInfoEntity coupon = cRepo.findByCiSeq(couponSeq);
               MemberCouponEntity mc = mcRepo.findByMemberAndSeq(member, coupon);
               
               if(mc==null || //보유중인 쿠폰이 아니거나
                    !mc.getMcUse() || //이미 사용했거나
                    (mc.getMcDate().getYear()!=LocalDate.now().getYear() //쿠폰 발급일자의 년도가 다르고
                    &&mc.getMcDate().getMonth()!=LocalDate.now().getMonth()) //쿠폰 발급일자의 월이 다르거나
               ){
                    resultMap.put("status", false);
                    resultMap.put("message", "사용할 수 없는 쿠폰이 선택되었습니다. 다시 시도해주세요.");
                    resultMap.put("code", HttpStatus.BAD_REQUEST);
                    resultMap.put("notOrders", c);
                    return resultMap;
               }

               order.setCoupon(coupon);
               mc.setMcUse(false);
               mcRepo.save(mc);
          }
          
          oiRepository.save(order);
          for(CartDetail ca : carts){
               OrderDetailEntity orderDetail = new OrderDetailEntity(ca);
               orderDetail.setOdOiseq(order);
               
               if(ca.getMenu().getBurger()!=null){
                    BurgerInfoEntity burger = biRepo.findByBiSeq(ca.getMenu().getBurger().getBiSeq());
                    burger.upSales(ca.getMenuCount()); //판매량 증가
                    biRepo.save(burger);
               }
               
               //재고 감소 기능
               discountStock(store, orderDetail);
               
               odRepo.save(orderDetail);
               for(IngredientVo ing : ca.getIngredient()){
                    OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity();
                    IngredientsInfoEntity i = iiRepo.findByIiSeq(ing.getIngredirentSeq());
                    orderIngredient.setIngredient(i);
                    orderIngredient.setOrderdetail(orderDetail);
                    discountIngredientStock(store, i);
                    oidRepo.save(orderIngredient);
               }
               LatelyDeliveryEntity latelyDelivery = ldRepo.findByLdAddressAndLdDetailAddress(address, detailAddress);
               if(latelyDelivery==null){
                    latelyDelivery = new LatelyDeliveryEntity(null, member, address, detailAddress, order.getOiOrderTime());
               }else{
                    latelyDelivery.setLdDelDate(order.getOiOrderTime());
               }
               ldRepo.save(latelyDelivery);
          }
          
          resultMap.put("status", true);
          resultMap.put("message", "주문이 완료되었습니다.");
          resultMap.put("code", HttpStatus.ACCEPTED);
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
               EventInfoEntity event = c.getEventMenu();
               if(burger!=null){
                    BurgerStockEntity bs = bsRepo.findByStoreAndBurger(store, burger);
                    if(bs.getBsStock()<c.getMenuCount()){
                         return false; //재고없음
                    }
               }
               if(dog!=null){
                    DogStockEntity dogstock = dogsRepo.findByStoreAndDog(store, dog);
                    if(dogstock.getDogsStock()<c.getMenuCount()){
                         return false; //재고없음
                    }
               }
               if(drink!=null){
                    DrinkStockEntity ds = dsRepo.findByStoreAndDrink(store, drink);
                    if(ds.getDsStock()<c.getMenuCount()){
                         return false; //재고없음
                    }
               }
               if(side!=null){
                    SideStockEntity ss = ssRepo.findByStoreAndSide(store, side);
                    if(ss.getSsStock()<c.getMenuCount()){
                         return false; //재고없음
                    }
               }
               if(event!=null){
                    EventStockEntity es = esRepo.findByStoreAndEvent(store, event);
                    if(es.getEsStock()<c.getMenuCount()){
                         return false; //재고없음
                    }
               }
               if(c.getIngredient().size()!=0){
                    for(IngredientVo i : c.getIngredient()){
                         IngredientsInfoEntity ingredientsInfoEntity = iiRepo.findByIiSeq(i.getIngredirentSeq());
                         IngredientsStockEntity ing = isRepo.findByStoreAndIngredient(store, ingredientsInfoEntity);
                         if(ing.getIsStock()<c.getMenuCount()){
                              return false;
                         }
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
          if(order.getOiStatus()!=1 && order.getOiStatus()!=2){
               map.put("status", false);
               map.put("message", "이미 배송중이거나 배송이 완료된 주문은 취소할 수 없습니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }
          order.setOiStatus(5);
          List<OrderDetailEntity> orderDetails = odRepo.findByOdOiseq(order);

          //재고 복구, 판매량 복구 안됨
          for(OrderDetailEntity od : orderDetails){
               StoreInfoEntity store = order.getStore();
               BurgerInfoEntity burger = od.getOdBiseq().getBurger();
               DogInfoEntity dog = od.getOdBiseq().getDog();
               DrinkInfoEntity drink = od.getOdBiseq().getDrink();
               SideInfoEntity side = od.getOdBiseq().getSide();
               EventInfoEntity event = od.getOdEiSeq();
               if(burger!=null){
                    BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, burger);
                    int stock = burgerStock.getBsStock()+od.getOdCount();
                    int sale = burger.getBiSalesRate()-od.getOdCount();
                    burger.setBiSalesRate(sale);
                    burgerStock.setBsStock(stock);
               }
               if(dog!=null){
                    DogStockEntity dogStock = dogsRepo.findByStoreAndDog(store, dog);
                    int dogSto = dogStock.getDogsStock() + od.getOdCount();
                    dogStock.setDogsStock(dogSto);
               }
               if(drink!=null){
                    DrinkStockEntity drinkStock = dsRepo.findByStoreAndDrink(store, od.getOdBiseq().getDrink());
                    int dStock = drinkStock.getDsStock()  + od.getOdCount();
                    drinkStock.setDsStock(dStock);
               }
               if(side!=null){
                    SideStockEntity sideStock = ssRepo.findByStoreAndSide(store, od.getOdBiseq().getSide());
                    int sStock = sideStock.getSsStock() + od.getOdCount();
                    sideStock.setSsStock(sStock);
               }
               if(event!=null){
                    EventStockEntity eventStock = esRepo.findByStoreAndEvent(store, od.getOdEiSeq());
                    int eStock = eventStock.getEsStock() + od.getOdCount();
                    eventStock.setEsStock(eStock);
               }

          }

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
               // order.addOrderDetail(orderDetailVo);
               resultOrder.add(order);
          }
          
          map.put("status", true);
          map.put("message", "주문 내역을 조회했습니다.");
          map.put("code", HttpStatus.ACCEPTED);
          map.put("list", resultOrder);
          return map;
     }

     public Map<String, Object> showDetailOrderList(LoginUserVO login, Long seq) {
          Map<String, Object> map = new LinkedHashMap<>();
           // MemberInfoEntity member = mRepo.findByMiEmail(login.getEmail());
          MemberInfoEntity member = mRepo.findAll().get(0);
          OrderInfoEntity order = oiRepository.findByOiSeqAndMember(seq, member);
          if(order==null){
               map.put("status", false);
               map.put("message", "잘못된 주문번호입니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }    
          MyOrderViewVO myOrderVo = new MyOrderViewVO(order);
          List<OrderDetailEntity> orderDetail = odRepo.findByOdOiseq(order);
          for(OrderDetailEntity od : orderDetail){
               MyOrderDetailVO oDetailVO = new MyOrderDetailVO(od);
               List<OrderIngredientsDetailEntity> ing = oidRepo.findByOrderdetail(od);
               Set<OrderIngredientsVO> ingVo = new LinkedHashSet<>();
               for(OrderIngredientsDetailEntity i : ing){
                    OrderIngredientsVO iVo = new OrderIngredientsVO(i);
                    ingVo.add(iVo);
                    oDetailVO.addPrice(od);
               }
               oDetailVO.addOrderIngredients(ingVo);
               myOrderVo.addOrderDetail(oDetailVO);
          }
          myOrderVo.totalPrice();
          map.put("status", true);
          map.put("message", "해당주문을 상세조회했습니다.");
          map.put("code", HttpStatus.ACCEPTED);
          map.put("order", myOrderVo);
          return map;
     }

     //결제 페이지
     public Map<String, Object> orderPage(LoginUserVO login, StoreInfoEntity store, List<CartDetail> c, @Nullable Set<Long> seq){
               Map<String, Object> map = new LinkedHashMap<>();

               if(c==null || c.size()==0){ 
                    map.put("status", false);
                    map.put("message", "아직 카트에 아무것도 추가되지않았습니다. 장바구니에 메뉴를 먼저 담아주세요.");
                    map.put("code", HttpStatus.BAD_REQUEST);
                    return map;
               }

               // MemberInfoEntity member = mRepo.findByMiEmail(login.getEmail());
               MemberInfoEntity member = mRepo.findAll().get(0); //지워야함

               OrderDeliveryVO order = new OrderDeliveryVO("임시주소", member, store); //주소 임시로 적음. 주소선택 기능 구현되면 수정해야함
               List<CartVo> carts = new ArrayList<>();
               for(CartDetail cart : c){
                    if(seq==null || seq.size()==0 ){ //주문할 메뉴를 선택하지 않았다면 모두 주문으로 처리
                         CartVo cVo = new CartVo(cart);
                         carts.add(cVo);
                    }else{
                         for(Long no : seq){
                              if(no==cart.getCartSeq()){
                                   cart.ingredientFreeMenu();
                                   CartVo cVo = new CartVo(cart);
                                   carts.add(cVo);
                              }
                         }
                    }
                    order.addPrice(cart);
               }
               if(carts.size()==0){
                    map.put("status", false);
                    map.put("message", "카트 번호를 잘못 입력하셨습니다.");
                    map.put("code", HttpStatus.BAD_REQUEST);
                    return map;
               }
               
               order.orderMenuSetting(carts);
               
               List<MemberCouponEntity> memberCoupon = mcRepo.findByMember(member);
               List<CouponVO> couponList = new ArrayList<>();
               if(memberCoupon.size()!=0){
                    for(MemberCouponEntity m : memberCoupon){
                         CouponVO coVo = new CouponVO(m);
                         couponList.add(coVo);
                    }
                    order.couponSetting(couponList);
               }
               
               map.put("status", true);
               map.put("message", "결제 페이지를 조회하였습니다.");
               map.put("code", HttpStatus.ACCEPTED);
               map.put("info", order);

               return map;
     }
}
