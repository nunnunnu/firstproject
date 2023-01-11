package com.green.firstproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;

@SpringBootTest
public class HiaBurgerAddTest {
    @Autowired BurgerInfoRepository bRepo;
    @Autowired CategoryRepository cateRepo;
  
    
}
