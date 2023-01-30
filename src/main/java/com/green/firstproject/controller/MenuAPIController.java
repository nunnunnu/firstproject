package com.green.firstproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.service.category.MenuService;
import com.green.firstproject.service.menu.MenuInfoService;

@RestController
@RequestMapping("/api/menu")
public class MenuAPIController {
    @Autowired MenuInfoRepository menuRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired SideInfoRepository sRepo;
    @Autowired MenuService mService;
    @Autowired MenuInfoService miService;

    @GetMapping("/all")
    public ResponseEntity<Object> getMenuList(Pageable pageable){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", menuRepo.findAll(pageable));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @GetMapping("/category")
    public Map<String, Object> selectCategories(@RequestParam Long seq) {
        Map<String, Object> resultMap = mService.cateSeq(seq);
        return resultMap;
    }
    //옵션 조회
    @GetMapping("/sideopt")
    public ResponseEntity<Object> getSideOptionInfo(@RequestParam Long seq){
        Map<String, Object> map = miService.getSideOptionInfo(seq);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    //음료 옵션 조회
    @GetMapping("/drinkopt")
    public ResponseEntity<Object> getDrinkOptionInfo(@RequestParam Long seq){
        Map<String, Object> map = miService.getDrinkOptionInfo(seq);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    //판매 메뉴 조회
    @GetMapping("/{type}/{seq}")
    public ResponseEntity<Object> getSellerMenu(
            @PathVariable String type,
            @PathVariable Long seq
        ){
        Map<String, Object> map = miService.getSellerMenu(type, seq);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    @GetMapping("/ingredient/{seq}")
    public ResponseEntity<Object> getIngredient(@PathVariable Long seq){
        Map<String, Object> resultMap = miService.showIngredient(seq);
        return new ResponseEntity<Object>(resultMap,(HttpStatus)resultMap.get("code"));
    }
    
}


