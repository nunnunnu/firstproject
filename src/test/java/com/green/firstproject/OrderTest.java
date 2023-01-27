package com.green.firstproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberCouponEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;
import com.green.firstproject.repository.master.CouponInfoRepository;
import com.green.firstproject.repository.master.PaymentInfoRepository;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberCouponRepository;
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
import com.green.firstproject.repository.stock.IngredientsStockRepository;

@SpringBootTest
@Transactional
public class OrderTest {
     
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
     @Autowired IngredientsStockRepository isRepo;
     @Autowired CouponInfoRepository cRepo;
     @Autowired MemberCouponRepository mcRepo;

     @Test
     void 주문DB저장(){
          //주문
          PaymentInfoEntity pay = piRepo.findAll().get(0);
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "이름", "010-0000-0000", 1, LocalDate.now(), null, null);
          StoreInfoEntity store = new StoreInfoEntity(null, "매장이름", "매장주소", "상세주소", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1);
          OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, 1, pay, null, "조심히와주세요", "대구 중구 동성로999길 건물 1층"); 

          mRepo.save(member);
          siRepo.save(store);

          MemberCouponEntity coupon = new MemberCouponEntity(null, LocalDate.now(), true, member, cRepo.findAll().get(0));
          
          oiRepository.save(order);

          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());

          OrderDetailEntity orderDetail = new OrderDetailEntity();
          orderDetail = orderDetail.builder().odCount(1).odBiseq(menu).odOiseq(order).build();
          
          odRepo.save(orderDetail);

          OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity(iiRepo.findAll().get(0), orderDetail);
          oidRepo.save(orderIngredient);
          coupon.setMcUse(false);

          OrderInfoEntity findOrder = oiRepository.findById(order.getOiSeq()).get();
          OrderDetailEntity findOrderDetail = odRepo.findById(orderDetail.getOdSeq()).get();
          OrderIngredientsDetailEntity findOrderIngredient = oidRepo.findById(orderIngredient.getOdiSeq()).get();

          //DB저장 여부 확인
          Assertions.assertThat(order).isEqualTo(findOrder);
          Assertions.assertThat(orderDetail).isEqualTo(findOrderDetail);
          Assertions.assertThat(orderIngredient).isEqualTo(findOrderIngredient);

          

          


     }

     @Test
     void 재고감소(){

          StoreInfoEntity store = new StoreInfoEntity(null, "매장이름", "매장주소", "상세주소", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1);
          siRepo.save(store);

          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());
          //재고감소
          BurgerStockEntity burgerStock = new BurgerStockEntity(null, store, menu.getBurger(), 100);
          bsRepo.save(burgerStock);
          
          burgerStock.setBsStock(burgerStock.getBsStock()-1);

          IngredientsInfoEntity ingredient = new IngredientsInfoEntity(null, "재료", 5000, "파일", "uri", null);

          IngredientsStockEntity ing = new IngredientsStockEntity(null, store, ingredient, 100);
          ing.setIsStock(ing.getIsStock()-1);

          //재고 감소 기능 확인
          Assertions.assertThat(99).isEqualTo(burgerStock.getBsStock());
          Assertions.assertThat(99).isEqualTo(ing.getIsStock());

     }

     @Test
     void 판매량증가(){
          //주문
          MenuInfoEntity menu = menuRepo.findByMenuSeq(menuRepo.findAll().get(0).getMenuSeq());

          //판매량 증가
          int originSales = menu.getBurger().getBiSalesRate();
          menu.getBurger().upSales(1);

          //판매량 증가 확인

          Assertions.assertThat(originSales+1).isEqualTo(menu.getBurger().getBiSalesRate());
     }

     @Test
     void 쿠폰사용여부변경(){
          //주문
          MemberInfoEntity member = new MemberInfoEntity(null, "user999@test.com", "123456", "이름", "010-0000-0000", 1, LocalDate.now(), null, null);
          mRepo.save(member);
          MemberCouponEntity coupon = new MemberCouponEntity(null, LocalDate.now(), true, member, cRepo.findAll().get(0));
          mcRepo.save(coupon);
          
          coupon.setMcUse(false);

          //쿠폰 사용여부 변경 확인
          Assertions.assertThat(coupon.getMcUse()).isFalse();
     }
}
