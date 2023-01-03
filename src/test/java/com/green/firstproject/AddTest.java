package com.green.firstproject;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.BurgerInfoEntity;
import com.green.firstproject.entity.CategoryEntity;
import com.green.firstproject.repository.BurgerInfoRepository;
import com.green.firstproject.repository.CategoryRepository;

@SpringBootTest

public class AddTest {
     @Autowired BurgerInfoRepository biRepo;
     @Autowired CategoryRepository cRepo;

     @Test
     void 버거정보추가(){
          Optional<CategoryEntity> c = cRepo.findById(1L);
          BurgerInfoEntity b = new BurgerInfoEntity(null, "치즈버거", c.get(), "치즈버거입니다", "file.jpg", "치즈버거", null, null);
          System.out.println(b);
          biRepo.save(b);
     }
}
