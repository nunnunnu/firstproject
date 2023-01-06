package com.green.firstproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.green.firstproject.service.menu.HiaBurgerService;
import com.green.firstproject.vo.menu.HiaBurgerAddVO;
import com.green.firstproject.vo.menu.HiaDogAddVO;
import com.green.firstproject.vo.menu.HiaDrinkAddVO;
import com.green.firstproject.vo.menu.HiaIngredAddVO;
import com.green.firstproject.vo.menu.HiaSideAddVO;


@RestController
@RequestMapping("/add")
public class HiaAddMenuController {
    @Autowired HiaBurgerService bService;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")

    @PostMapping("/burger")
    public ResponseEntity<Object> burgerAdd(@RequestBody HiaBurgerAddVO data){
        Map<String, Object> resultMap = bService.addBurger(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    
    @PostMapping("/side")
    public ResponseEntity<Object> sideAdd(@RequestBody HiaSideAddVO data){
        Map<String, Object> resultMap = bService.addSide(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/drink")
    public ResponseEntity<Object> drinkAdd(@RequestBody HiaDrinkAddVO data){
        Map<String, Object> resultMap = bService.addDrink(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/dog")
    public ResponseEntity<Object> dogAdd(@RequestBody HiaDogAddVO data){
        Map<String, Object> resultMap = bService.addDog(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @PostMapping("/ingred")
    public ResponseEntity<Object> ingredAdd(@RequestBody HiaIngredAddVO data){
        Map<String, Object> resultMap = bService.addIngredients(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
