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
          String message,
          Long couponSeq,
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
          member = mRepo.findAll().get(0);
          PaymentInfoEntity pay = piRepo.findByPaySeq(paySeq);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null, message, address+" "+detailAddress); 
          
          if(couponSeq!=null){
               MemberCouponEntity mc = mcRepo.findByMcSeq(couponSeq);
               
               if(mc==null){
                    resultMap.put("status", false);
                    resultMap.put("message", "쿠폰번호가 잘못되었습니다.");
                    resultMap.put("code", HttpStatus.BAD_REQUEST);
                    return resultMap;
               }else if(!mc.getMcUse() && //사용하지 않았고
                    mc.getMcDate().getYear()==LocalDate.now().getYear() 
                    &&mc.getMcDate().getMonth()==LocalDate.now().getMonth() 
               ){
                    order.setCoupon(mc.getCoupon());
                    mc.setMcUse(true);
                    mcRepo.save(mc);
               }else{
                    resultMap.put("status", false);
                    resultMap.put("message", "사용불가능한 쿠폰이 포함되어있습니다.");
                    resultMap.put("code", HttpStatus.BAD_REQUEST);
                    return resultMap;
               }
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
               BurgerInfoEntity burger = od.getOdBiseq().getBurger();
               if(burger!=null){
                    int sale = burger.getBiSalesRate()-od.getOdCount();
                    burger.setBiSalesRate(sale);
               }
          }

          oiRepository.save(order);
          map.put("status", true);
          map.put("message", "주문이 취소되었습니다.");
          map.put("code", HttpStatus.ACCEPTED);
          return map;
     }
     
     //주문 리스트 조회
     public Map<String, Object> showMyOrder(MemberInfoEntity member){
          Map<String, Object> map = new LinkedHashMap<>();

          if(member==null){
               map.put("status", false);
               map.put("message", "회원번호가 잘못되었습니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }
          
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
               if(orderDetails!=null){
                    for(OrderDetailEntity od : orderDetails){                                            
                         List<OrderIngredientsDetailEntity> ingredients = oidRepo.findByOrderdetail(od);
                         if(ingredients!=null){
                              int count=0;
                              for(OrderIngredientsDetailEntity i : ingredients){
                                   if(i.getIngredient().getIiPrice()==0){
                                        count++;
                                   }else{
                                        order.addIngredientPrice(i, od.getOdCount());
                                   }
                              }
                              if(count>1){
                                   order.addCheckIngredientPrice(od.getOdCount());
                              }
                         }
                         order.setOrderPrice(od);
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

     public Map<String, Object> showDetailOrderList(MemberInfoEntity member, Long seq) {
          Map<String, Object> map = new LinkedHashMap<>();
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
               oDetailVO.addOrderIngredients(ingVo, od.getOdCount());
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

     
}
