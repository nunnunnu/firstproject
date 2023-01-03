package com.green.firstproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;


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
