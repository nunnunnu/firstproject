package com.green.firstproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuAPIController {
    @Autowired MenuInfoRepository menuRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired SideInfoRepository sRepo;

    @GetMapping("/all")
    public ResponseEntity<Object> getMenuList(Pageable pageable){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", menuRepo.findAll(pageable));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @Autowired MenuService mService;
    @GetMapping("/category")
    public Map<String, Object> selectCategories(@RequestParam Long seq) {
        Map<String, Object> resultMap = mService.cateSeq(seq);
        // List<BurgerInfoEntity> list = bRepo.findAll();
        // resultMap.put("total", list.size());
        // resultMap.put("list", list);
        return resultMap;
    }

}
