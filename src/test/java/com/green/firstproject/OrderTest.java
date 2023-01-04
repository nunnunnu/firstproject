package com.green.firstproject;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.repository.master.CouponInfoRepository;
import com.green.firstproject.repository.master.GradeInfoRepository;
import com.green.firstproject.repository.master.PaymentInfoRepository;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.LatelyDeliveryRepository;
import com.green.firstproject.repository.member.MemberCouponRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.repository.member.MyDeliveryRepository;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
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

@SpringBootTest
public class OrderTest {

     @Autowired OrderInfoRepository oiRepo;
     @Autowired OrderDetailRepository odRepo;
     @Autowired BurgerInfoRepository biRepo;
     @Autowired SideOptionRepository soRepo;
     @Autowired MemberInfoReposiroty miRepo;
     @Autowired StoreInfoRepository siRepo;
     @Autowired PaymentInfoRepository piRepo;
     @Autowired MenuInfoRepository menuRepo;
     @Autowired CouponInfoRepository ciRepo;
     @Autowired IngredientsInfoRepository iiRepo;
     @Autowired OrderIngredientsDetailRepository oidRepo;
     
     @Test
     @Transactional
     void 주문(){
          // MemberInfoEntity m = miRepo.findAll().get(0);
          // System.out.println(m);
          // PaymentInfoEntity p = new PaymentInfoEntity(null, "만나서", 1);
          // piRepo.save(p);
          
          // OrderInfoEntity o = new OrderInfoEntity(null, m, LocalDateTime.now(), siRepo.findAll().get(0), null, piRepo.findAll().get(0), ciRepo.findAll().get(0));
          // o = oiRepo.save(o);
          // // System.out.println(menuRepo.findAll().get(0));
          // System.out.println(menuRepo.findAll().get(0));
          // OrderDetailEntity od = new OrderDetailEntity(null, 1, o, menuRepo.findAll().get(0), null);
          // // System.out.println(od);
          // odRepo.save(od);
          // OrderDetailCompositionEntity odc = new OrderDetailCompositionEntity(null, od, soRepo.findBySoSeq(1L), null);
          // odcRepo.save(odc);
          // System.out.println(iiRepo.findAll().get(0));
          // OrderIngredientsDetailEntity oid = new OrderIngredientsDetailEntity(null, od, iiRepo.findAll().get(0));
          // oidRepo.save(oid);
     }

     @Autowired BurgerStockRepository bsRepo;
     @Autowired CategoryRepository cRepo;
     @Autowired DogInfoRepository diRepo;
     @Autowired DogStockRepository dsRepo;
     @Autowired DrinkInfoRepository di2Repo;
     @Autowired DrinkStockRepository ds2Repo;
     @Autowired EventInfoRepository eiRepo;
     @Autowired EventStockRepository esRepo;
     @Autowired GradeInfoRepository giRepo;
     @Autowired IngredientsStockRepository isRepo;
     @Autowired LatelyDeliveryRepository ldRepo;
     @Autowired MemberCouponRepository mcRepo;
     @Autowired MyDeliveryRepository mdrRepo;
     @Autowired SideStockRepository ssRepo;
     @Autowired SideInfoRepository sideRepo;
     

     @Test
     void 확인(){
          System.out.println(odRepo.findAll());
     }

}
