package com.green.firstproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.BurgerInfoEntity;
import com.green.firstproject.repository.BurgerInfoRepository;

@SpringBootTest

public class AddTest {
     @Autowired BurgerInfoRepository biRepo;

     @Test
     void 버거정보추가(){
          BurgerInfoEntity b = new BurgerInfoEntity(null, "치즈버거", 3L, "치즈버거입니다", "file.jpg", "치즈버거", null, null);
          biRepo.save(b);
     }
}
