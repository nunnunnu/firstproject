package com.green.firstproject.service.menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;

@Service
public class MenuInfoService {
    @Autowired MenuInfoRepository menuRepo;
    @Autowired BurgerInfoRepository burgerRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired DrinkInfoRepository drinkRepo;
    @Autowired SideInfoRepository sideRepo;
    public Map<String, Object> getBuregerInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        BurgerInfoEntity b = burgerRepo.findByBiSeq(seq);
        if(b==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 버거가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findByBurger(b);
        resultMap.put("list", list);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }
    public Map<String, Object> getDogInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DogInfoEntity dog = dogRepo.findByDogSeq(seq);
        if(dog==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 독퍼가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findBydog(dog);
        resultMap.put("list", list);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }
    public Map<String, Object> getDrinkInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DrinkInfoEntity drink = drinkRepo.findByDiSeq(seq);
        if(drink==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 음료가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findByDrink(drink);
        if(list.size() == 0){
            resultMap.put("message", "해당하는 메뉴가 없습니다.");
        }
        List<MenuInfoEntity> list3 = menuRepo.findByDrink(drink);
        for(MenuInfoEntity m : list){
            if(m.getBurger()==null && m.getDog()==null && m.getSide()==null && m.getDrink()!=null){
                list3.add(m);
            }
        }
        resultMap.put("list", list3);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }
    public Map<String, Object> getSideInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SideInfoEntity side = sideRepo.findBySideSeq(seq);
        if(side==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 사이드 메뉴가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findBySide(side);
        if(list.size() == 0){
            resultMap.put("message", "해당하는 메뉴가 없습니다.");
        }
        List<MenuInfoEntity> list2 = new ArrayList<>();
        for(MenuInfoEntity m : list){
            if(m.getBurger()==null && m.getDrink()==null && m.getDog()==null && m.getSide()!=null){
                list2.add(m);
            }
        }
        resultMap.put("list", list2);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }
}
