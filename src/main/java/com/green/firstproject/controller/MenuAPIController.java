package com.green.firstproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.green.firstproject.service.menu.BurgerService;
import com.green.firstproject.service.menu.MenuInfoService;
import com.green.firstproject.vo.menu.BurgerAddVO;
import com.green.firstproject.vo.menu.DogAddVO;
import com.green.firstproject.vo.menu.DrinkAddVO;
import com.green.firstproject.vo.menu.EventAddVO;
import com.green.firstproject.vo.menu.IngredAddVO;
import com.green.firstproject.vo.menu.SideAddVO;

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
    @Autowired BurgerService bService;

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

    // @GetMapping("/new")
    // public Map<String, Object> getMenuNew(@RequestParam @Nullable Long seq){
    //     Map<String, Object> resultMap = miService.getNewMenu(seq);
    //     return resultMap;
    // }
    
    @GetMapping("/burger")
    public ResponseEntity<Object> getBuregerInfo(@RequestParam Long seq) {
            Map<String, Object> map = miService.getBuregerInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    @GetMapping("/dog")
    public ResponseEntity<Object> getDogInfo(@RequestParam Long seq){
            Map<String, Object> map = miService.getDogInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
    @GetMapping("/drink")
    public ResponseEntity<Object> getDrinkInfo(@RequestParam Long seq){
            Map<String, Object> map = miService.getDrinkInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
    @GetMapping("/side")
    public ResponseEntity<Object> getSideInfo(@RequestParam Long seq){
            Map<String, Object> map = miService.getSideInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
    @GetMapping("/sideopt")
    public ResponseEntity<Object> getSideOptionInfo(@RequestParam Long seq){
        Map<String, Object> map = miService.getSideOptionInfo(seq);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    @GetMapping("/drinkopt")
    public ResponseEntity<Object> getDrinkOptionInfo(@RequestParam Long seq){
        Map<String, Object> map = miService.getDrinkOptionInfo(seq);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }

    @PostMapping("/burger")
    public ResponseEntity<Object> burgerAdd(@RequestBody BurgerAddVO data){
        Map<String, Object> resultMap = bService.addBurger(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    
    @PostMapping("/side")
    public ResponseEntity<Object> sideAdd(@RequestBody SideAddVO data){
        Map<String, Object> resultMap = bService.addSide(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/drink")
    public ResponseEntity<Object> drinkAdd(@RequestBody DrinkAddVO data){
        Map<String, Object> resultMap = bService.addDrink(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/dog")
    public ResponseEntity<Object> dogAdd(@RequestBody DogAddVO data){
        Map<String, Object> resultMap = bService.addDog(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/ingred")
    public ResponseEntity<Object> ingredAdd(@RequestBody IngredAddVO data){
        Map<String, Object> resultMap = bService.addIngredients(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/event")
    public ResponseEntity<Object> eventAdd(@RequestBody EventAddVO data){
        Map<String, Object> resultMap = bService.addEvent(data);
        return new ResponseEntity<Object>(resultMap,(HttpStatus)resultMap.get("code"));
    }
    // @GetMapping("/best")
    // public ResponseEntity<Object> bestMenu(@RequestParam @Nullable Long seq){
    //     Map<String, Object> map = miService.getBestMenu(seq);
    //     return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    // }
}
