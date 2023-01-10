package com.green.firstproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.service.category.MenuService;
import com.green.firstproject.service.menu.MenuInfoService;
import com.green.firstproject.vo.menu.HiaMenuListVO;

import io.micrometer.common.lang.Nullable;

@RestController
@RequestMapping("/api/menu")
public class MenuAPIController {
    @Autowired MenuInfoRepository menuRepo;
    @Autowired DrinkInfoRepository dRepo;
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

    @GetMapping("/new")
    public Map<String, Object> getMenuNew(@RequestParam @Nullable Long seq){
        Map<String, Object> resultMap = miService.getNewMenu(seq);
        return resultMap;
    }

}
