package com.green.firstproject;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.order.OrderInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;

@SpringBootTest
public class AddTest {
     @Autowired BurgerInfoRepository biRepo;
     @Autowired CategoryRepository cRepo;
     @Autowired OrderInfoEntity oiRepo;

     @Test
     @Transactional
     void 버거정보추가(){
          Optional<CategoryEntity> c = cRepo.findById(1L);
          BurgerInfoEntity b = new BurgerInfoEntity(null, "치즈버거1", c.get(), "치즈버거입니다", "file.jpg", "치즈버거", null, null);
          biRepo.save(b);
     }
     @Test
     @Transactional
     void 카테고리추가(){
          CategoryEntity c = new CategoryEntity(null, "카테고리1");
          cRepo.save(c);
     }
     @Test
     void 주문(){
          
     }
}
