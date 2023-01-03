package com.green.firstproject;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< HEAD
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
=======
import com.green.firstproject.entity.SideInfoEntity;
import com.green.firstproject.repository.BurgerInfoRepository;
import com.green.firstproject.repository.CategoryRepository;
import com.green.firstproject.repository.DrinkInfoRepository;
import com.green.firstproject.repository.SideInfoRepository;
>>>>>>> jeun

@SpringBootTest
class FirstprojectApplicationTests {

	@Autowired BurgerInfoRepository burgerRepo;
	@Autowired SideInfoRepository sideRepo;
	@Autowired DrinkInfoRepository drinkRepo;
	@Autowired CategoryRepository category;
	@Test
	void burgerList(){
        System.out.println(burgerRepo.findAll());
    }
	@Test
	void sideList(){
		System.out.println(sideRepo.findAll());
	}
	
}
