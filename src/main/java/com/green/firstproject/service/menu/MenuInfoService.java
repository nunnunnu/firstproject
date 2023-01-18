package com.green.firstproject.service.menu;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.vo.menu.BestMenuVO;
import com.green.firstproject.vo.menu.MenuListVO;
import com.green.firstproject.vo.menu.option.DrinkOptionVO;
import com.green.firstproject.vo.menu.option.SideOptionVO;

@Service
public class MenuInfoService {
    @Autowired MenuInfoRepository menuRepo;
    @Autowired BurgerInfoRepository burgerRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired DrinkInfoRepository drinkRepo;
    @Autowired SideInfoRepository sideRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired SideOptionRepository sideoptRepo;
    @Autowired DrinkOptionRepository drnikoptRepo;
    @Autowired EventInfoRepository eventRepo;

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

    
    public Map<String, Object> getSideOptionInfo(Long seq) { 
        Map<String, Object> resultMap = new LinkedHashMap<>();
        MenuInfoEntity menu = menuRepo.findByMenuSeq(seq);
        if(menu == null){
            resultMap.put("status", false);
            resultMap.put("message", "메뉴 번호가 잘못되었습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<SideOptionEntity> list = new ArrayList<>();
        if(menu.getMenuSize() == 1){
            list = sideoptRepo.findAll();
        }else if(menu.getMenuSize() == 2){
            list = sideoptRepo.findBySoSize(menu.getMenuSize());
        }
            List<SideOptionVO> result = new ArrayList<>();
            for(SideOptionEntity s : list){
                SideOptionVO side = new SideOptionVO(s);
                result.add(side);
            }
            
            resultMap.put("message", "사이드 옵션을 조회하였습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("list", result);
            return resultMap;
        }

    public Map<String, Object> getDrinkOptionInfo(Long seq) { 
        Map<String, Object> resultMap = new LinkedHashMap<>();
        MenuInfoEntity menu = menuRepo.findByMenuSeq(seq);
        if(menu == null){
            resultMap.put("status", false);
            resultMap.put("message", "메뉴 번호가 잘못되었습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<DrinkOptionEntity> list = new ArrayList<>();
        if(menu.getMenuSize() == 1){
            list = drnikoptRepo.findAll();
        }else if(menu.getMenuSize() == 2){
            list = drnikoptRepo.findByDoSize(menu.getMenuSize());
        }
        List<DrinkOptionVO> result = new ArrayList<>();
        for(DrinkOptionEntity d : list){
            DrinkOptionVO drink = new DrinkOptionVO(d);
            result.add(drink);
        }
        
        resultMap.put("message", "음료 옵션을 조회하였습니다");
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("list", result);
        return resultMap;
    }

    // public Map<String, Object> getNewMenu(Long seq){
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     LocalDate now = LocalDate.now();
    //     // SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    //     // f.format(now);
    //     List<MenuListVO> result = new ArrayList<>();
    //     for(BurgerInfoEntity b : burgerRepo.findAll()){
    //         MenuListVO data = new MenuListVO(b);
    //         // if((Period.between(now, data.getRegDt()).getDays() <= 30)&&(Period.between(now, data.getRegDt()).getYears() == 0)){
    //         //     data.setStatus(true);
    //         // }
    //         result.add(data);
    //     }
    //     resultMap.put("status", true);
    //     resultMap.put("message", "신제품 목록 입니다.");
    //     resultMap.put("code", HttpStatus.ACCEPTED);
    //     resultMap.put("list", result);
    //     return resultMap;
    // }
    
}
