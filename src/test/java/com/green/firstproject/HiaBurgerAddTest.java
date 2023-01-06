package com.green.firstproject;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.vo.menu.HiaBurgerAddVO;

@SpringBootTest
public class HiaBurgerAddTest {
    @Autowired BurgerInfoRepository bRepo;
    @Autowired CategoryRepository cateRepo;
  
    
}
