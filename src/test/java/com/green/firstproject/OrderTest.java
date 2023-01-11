package com.green.firstproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
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

     @Test
     // @Transactional
     void 일반메뉴주문(){
          // MemberInfoEntity member = mRepo.findAll().get(0);
          // StoreInfoEntity store = siRepo.findAll().get(0);
          // List<CartDetail> carts = new ArrayList<>();
          // for(int i=0;i<2;i++){
          //      MenuInfoEntity menu = menuRepo.findAll().get(i);
          //      CartDetail cart = new CartDetail(1L,1, menu);
          //      if(menu.getBurger()!=null && menu.getDrink()!=null && menu.getSide()!=null){
          //           SideOptionEntity sideOption = soRepo.findAll().get(i);
          //           DrinkOptionEntity drinkOption = diRepo.findAll().get(i);
          //           cart.setSide(sideOption);
          //           cart.setDrink(drinkOption);
          //      }
          //      List<IngredientsInfoEntity> ingredients = new ArrayList<>();
               
          //      cart.addIngredient(iiRepo.findAll().get(i));
          //      cart.addIngredient(iiRepo.findAll().get(i+1));
               
          //      carts.add(cart);
               
          // }

          // OrderInfoEntity order = new OrderInfoEntity(null, member, LocalDateTime.now(), store, null, piRepo.findAll().get(0), null, null);
          // Long orderSeq = oiRepository.save(order).getOiSeq();
          // // order = oiRepository.findByOiSeq(orderSeq);

          // OrderVO orderVo = new OrderVO(order);
          // OrderIngredientsDetailEntity orderIngredient = new OrderIngredientsDetailEntity();
          // OrderDetailVO oDetailVO;
          // for(CartDetail c : carts){
          //      OrderDetailEntity orderDetail = new OrderDetailEntity(c);
          //      orderDetail.setOdOiseq(order);
          //      odRepo.save(orderDetail);
          //      for(IngredientVo i : c.getIngredient()){
          //           // orderIngredient.setIngredient();
          //           // orderIngredient.setOrderdetail(orderDetail);
          //           // oidRepo.save(orderIngredient);
          //      }
          //      oDetailVO = new OrderDetailVO(orderDetail);
          // }
          
          


     }
     @Test
     void 확인(){
     }
}
