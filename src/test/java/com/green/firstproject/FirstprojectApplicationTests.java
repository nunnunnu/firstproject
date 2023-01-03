package com.green.firstproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.repository.BurgerInfoRepository;

@SpringBootTest
class FirstprojectApplicationTests {

	@Autowired BurgerInfoRepository burgerRepo;
	@Test
	void 카테고리별조회(){
        System.out.println(burgerRepo.findAll());
    }

}
