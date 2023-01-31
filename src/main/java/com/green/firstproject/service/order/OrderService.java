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
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.entity.order.cart.CartVo;
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
import com.green.firstproject.vo.order.MyOrderDetailVO;
import com.green.firstproject.vo.order.MyOrderViewVO;
import com.green.firstproject.vo.order.OrderDeliveryVO;
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
          Long paySeq ,List<CartDetail> carts, 
          @Nullable String message,
          @Nullable Long couponSeq,
          String address, String detailAddress
     ){   
          Map<String, Object> resultMap = new LinkedHashMap<>();
          if(carts==null || carts.size()==0){ 
               resultMap.put("status", false);
               resultMap.put("message", "주문 상품이 없습니다.");
               resultMap.put("code", HttpStatus.BAD_REQUEST);
               return resultMap;
          }
          List<Long> menuSeq = new ArrayList<>();
          for(CartDetail c : carts){
               menuSeq.add(c.getMenu());
          }
          if(menuRepo.countMenu(menuSeq)==0){
               resultMap.put("status", false);
               resultMap.put("message", "일치하는 메뉴가 존재하지 않습니다. 번호를 다시 확인해주세요.");
               resultMap.put("code", HttpStatus.BAD_REQUEST);
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
                    return resultMap;
               }

               order.setCoupon(coupon);
               mc.setMcUse(false);
               mcRepo.save(mc);
          }
          
          oiRepository.save(order);
          for(CartDetail c : carts){
               OrderDetailEntity orderDetail = new OrderDetailEntity();

               MenuInfoEntity menu = menuRepo.findMenuSeq(c.getMenu());
               orderDetail.setOdBiseq(menu);
               if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
                    if(c.getSideOpt()!=null){
                         SideOptionEntity side = soRepo.findBySoSeq(c.getSideOpt());
                         orderDetail.setOdLsotSeq(side);
                    }
                    if(c.getDrinkOpt()!=null){
                         DrinkOptionEntity drink = diRepo.findByDoSeq(c.getDrinkOpt());
                         orderDetail.setOdLdotSeq(drink);
                    }
               }
               if(menu.getEvent()!=null){
                    if(c.getDrinkOpt()!=null){
                         DrinkOptionEntity drink = diRepo.findByDoSeq(c.getDrinkOpt());
                         orderDetail.setOdLdotSeq(drink);
                    }
                    if(c.getDrink2Opt()!=null){
                         DrinkOptionEntity drink2 = diRepo.findByDoSeq(c.getDrink2Opt());
                         orderDetail.setOdLdot2Seq(drink2);
                    }
               }

               orderDetail.setOdOiseq(order);
               orderDetail.setOdCount(c.getCount());

               if(menu.getBurger()!=null){
                    menu.getBurger().upSales(c.getCount()); //판매량 증가
                    biRepo.save(menu.getBurger());
               }
               
               odRepo.save(orderDetail);
               if(c.getIngredient()!=null){
                    List<IngredientsInfoEntity> ings = iiRepo.findByingSeq(c.getIngredient());
                    for(IngredientsInfoEntity i : ings){
                         OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity(i, orderDetail);
                         oidRepo.save(orderIngredient);
                    }
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
          return resultMap;
     }

     //기본메뉴 재고감소
     // public void discountStock(StoreInfoEntity store, OrderDetailEntity orderDetail){   
     //      if(orderDetail.getOdBiseq().getBurger()!=null){
     //           BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, orderDetail.getOdBiseq().getBurger());
     //           int bStock = burgerStock.getBsStock() - orderDetail.getOdCount();
     //           burgerStock.setBsStock(bStock);
     //      }
     //      if(orderDetail.getOdBiseq().getDog()!=null){
     //           DogStockEntity dogStock = dogsRepo.findByStoreAndDog(store, orderDetail.getOdBiseq().getDog());
     //           int dogSto = dogStock.getDogsStock() - orderDetail.getOdCount();
     //           dogStock.setDogsStock(dogSto);
     //      }
     //      if(orderDetail.getOdBiseq().getDrink()!=null){
     //           DrinkStockEntity drinkStock = dsRepo.findByStoreAndDrink(store, orderDetail.getOdBiseq().getDrink());
     //           int dStock = drinkStock.getDsStock() - orderDetail.getOdCount();
     //           drinkStock.setDsStock(dStock);
     //      }
     //      if(orderDetail.getOdBiseq().getSide()!=null){
     //           SideStockEntity side = ssRepo.findByStoreAndSide(store, orderDetail.getOdBiseq().getSide());
     //           int sideStock = side.getSsStock() - orderDetail.getOdCount();
     //           side.setSsStock(sideStock);
     //      }
     //      if(orderDetail.getOdEiSeq()!=null){
     //           EventStockEntity event = esRepo.findByStoreAndEvent(store, orderDetail.getOdEiSeq());
     //           int eventStock = event.getEsStock() - orderDetail.getOdCount();
     //           event.setEsStock(eventStock);
     //      }
     // }
     //재료 재고 감소
     // public void discountIngredientStock(StoreInfoEntity store, IngredientsInfoEntity ingredirent, int count){
     //      IngredientsStockEntity ing = isRepo.findByStoreAndIngredient(store, ingredirent);
     //      int ingStock = ing.getIsStock()-count;
     //      ing.setIsStock(ingStock);
     // }
     // 매장 재고 검사
     // public Boolean stockCheck(List<CartDetail> carts, StoreInfoEntity store){
     //      for(CartDetail c : carts){
     //           MenuInfoEntity menu = menuRepo.findMenuSeq(c.getMenu());
     //           BurgerInfoEntity burger = menu.getBurger();
     //           DogInfoEntity dog = menu.getDog();
     //           DrinkInfoEntity drink = menu.getDrink();
     //           SideInfoEntity side = menu.getSide();
     //           EventInfoEntity event = menu.getEvent();
     //           if(burger!=null){
     //                BurgerStockEntity bs = bsRepo.findByStoreAndBurger(store, burger);
     //                if(bs.getBsStock()<c.getCount()){
     //                     return false; //재고없음
     //                }
     //           }
     //           if(dog!=null){
     //                DogStockEntity dogstock = dogsRepo.findByStoreAndDog(store, dog);
     //                if(dogstock.getDogsStock()<c.getCount()){
     //                     return false; //재고없음
     //                }
     //           }
     //           if(drink!=null){
     //                DrinkStockEntity ds = dsRepo.findByStoreAndDrink(store, drink);
     //                if(ds.getDsStock()<c.getCount()){
     //                     return false; //재고없음
     //                }
     //           }
     //           if(side!=null){
     //                SideStockEntity ss = ssRepo.findByStoreAndSide(store, side);
     //                if(ss.getSsStock()<c.getCount()){
     //                     return false; //재고없음
     //                }
     //           }
     //           if(event!=null){
     //                EventStockEntity es = esRepo.findByStoreAndEvent(store, event);
     //                if(es.getEsStock()<c.getCount()){
     //                     return false; //재고없음
     //                }
     //           }
     //           if(c.getIngredient()!=null){
     //                // List<IngredientsInfoEntity> ingredients = iiRepo.findByingSeq(c.getIngredient());
     //                // for(IngredientVo i : ings){
     //                //      ingSeq.add(i.getIngredirentSeq());
     //                // }
     //                // Set<IngredientsInfoEntity> ingredientsInfoEntity = iiRepo.findByingSeq(ingSeq);
     //                List<IngredientsStockEntity> ings = isRepo.findStoreAndIngredient(store, c.getIngredient());
     //                for(IngredientsStockEntity ing : ings){
     //                     if(ing.getIsStock()<c.getCount()){
     //                          return false;
     //                     }
     //                }
     //           }
     //      }
     //      return true;
     // }

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
          List<OrderDetailEntity> orderDetails = odRepo.findBurgerFetch(order);

          for(OrderDetailEntity od : orderDetails){
               // StoreInfoEntity store = order.getStore();
               BurgerInfoEntity burger = od.getOdBiseq().getBurger();
               // DogInfoEntity dog = od.getOdBiseq().getDog();
               // DrinkInfoEntity drink = od.getOdBiseq().getDrink();
               // SideInfoEntity side = od.getOdBiseq().getSide();
               if(burger!=null){
                    // BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, burger);
                    // int stock = burgerStock.getBsStock()+od.getOdCount();
                    int sale = burger.getBiSalesRate()-od.getOdCount();
                    burger.setBiSalesRate(sale);
                    // burgerStock.setBsStock(stock);
               }
               // if(dog!=null){
               //      DogStockEntity dogStock = dogsRepo.findByStoreAndDog(store, dog);
               //      int dogSto = dogStock.getDogsStock() + od.getOdCount();
               //      dogStock.setDogsStock(dogSto);
               // }
               // if(drink!=null){
               //      DrinkStockEntity drinkStock = dsRepo.findByStoreAndDrink(store, od.getOdBiseq().getDrink());
               //      int dStock = drinkStock.getDsStock()  + od.getOdCount();
               //      drinkStock.setDsStock(dStock);
               // }
               // if(side!=null){
               //      SideStockEntity sideStock = ssRepo.findByStoreAndSide(store, od.getOdBiseq().getSide());
               //      int sStock = sideStock.getSsStock() + od.getOdCount();
               //      sideStock.setSsStock(sStock);
               // }
               // if(event!=null){
               //      EventStockEntity eventStock = esRepo.findByStoreAndEvent(store, od.getOdEiSeq());
               //      int eStock = eventStock.getEsStock() + od.getOdCount();
               //      eventStock.setEsStock(eStock);
               // }

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
               List<OrderDetailEntity> orderDetails = odRepo.findFetchAll(o);
               OrderVO order = new OrderVO(o);
               // List<OrderDetailVO> orderDetailVo = new ArrayList<>();
               if(orderDetails!=null){
                    for(OrderDetailEntity od : orderDetails){                         
                         // OrderDetailVO orderDe = new OrderDetailVO(od);
                         // orderDe.addPrice(od);
                         // orderDetailVo.add(orderDe);
                         
                         order.setOrderPrice(od);
                         List<OrderIngredientsDetailEntity> ingredients = oidRepo.findByOrderdetail(od);
                         if(ingredients!=null){
                              int count=0;
                              for(OrderIngredientsDetailEntity i : ingredients){
                                   if(i.getIngredient().getIiPrice()==0){
                                        count++;
                                   }else{
                                        order.addIngredientPrice(i);
                                   }
                              }
                              if(count>1){
                                   order.addCheckIngredientPrice();
                              }
                         }
                    }   
               }
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
          List<OrderDetailEntity> orderDetail = odRepo.findFetchAll(order);
          for(OrderDetailEntity od : orderDetail){
               MyOrderDetailVO oDetailVO = new MyOrderDetailVO(od);
               List<OrderIngredientsDetailEntity> ing = oidRepo.findByOrderdetail(od);
               Set<OrderIngredientsVO> ingVo = new LinkedHashSet<>();
               for(OrderIngredientsDetailEntity i : ing){
                    OrderIngredientsVO iVo = new OrderIngredientsVO(i);
                    ingVo.add(iVo);
                    oDetailVO.ingredientName(i);
               }
               oDetailVO.addPrice(od);
               oDetailVO.addOrderIngredients(ingVo);
               if(oDetailVO.getComposition().equals("")){
                    oDetailVO.setComposition(null);
               }
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
     public Map<String, Object> orderPage(LoginUserVO login, StoreInfoEntity store, List<CartDetail> c){
          Map<String, Object> map = new LinkedHashMap<>();
          if(c==null || c.size()==0){ 
               map.put("status", false);
               map.put("message", "주문 메뉴를 선택하지 않으셨습니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }

          // MemberInfoEntity member = mRepo.findByMiEmail(login.getEmail());
          MemberInfoEntity member = mRepo.findAll().get(0); //지워야함

          OrderDeliveryVO order = new OrderDeliveryVO("임시주소", member, store); //주소 임시로 적음. 주소선택 기능 구현되면 수정해야함
          List<CartVo> carts = new ArrayList<>();
          for(CartDetail cart : c){
               MenuInfoEntity menu = menuRepo.findByMenuSeq(cart.getMenu());
               SideOptionEntity sideOpt = soRepo.findBySoSeq(cart.getSideOpt());
               DrinkOptionEntity drinkOpt = diRepo.findByDoSeq(cart.getDrinkOpt());
               DrinkOptionEntity drink2Opt = diRepo.findByDoSeq(cart.getDrink2Opt());
               List<IngredientsInfoEntity> ingredients = iiRepo.findByingSeq(cart.getIngredient());
               
               CartVo cVo = new CartVo(cart, menu, sideOpt, drinkOpt, drink2Opt, ingredients);
               carts.add(cVo);
               order.addPrice(cVo);

               order.orderMenuSetting(carts);
          }
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
