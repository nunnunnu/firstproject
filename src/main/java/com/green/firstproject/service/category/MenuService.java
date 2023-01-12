package com.green.firstproject.service.category;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.vo.menu.BurgerVO;
import com.green.firstproject.vo.menu.DogVO;
import com.green.firstproject.vo.menu.DrinkVO;
import com.green.firstproject.vo.menu.SideVO;

@Service
public class MenuService {
    @Autowired CategoryRepository cateRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired SideInfoRepository sRepo;
    @Autowired DogInfoRepository dogRepo;
    public Map<String, Object> cateSeq(Long seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        LocalDate now = LocalDate.now();
        CategoryEntity cate = cateRepo.findByCateSeq(seq);
        if (cate==null) {
            resultMap.put("status", false);
            resultMap.put("message", "결과가 존재하지않습니다");
            resultMap.put("code", HttpStatus.NOT_FOUND);
            return resultMap;
        }
        List<Object> list = new ArrayList<>();
        List<BurgerInfoEntity> burgerList = bRepo.findByCate(cate);
        List<BurgerVO> burgerResult = new ArrayList<>();
        for(BurgerInfoEntity b : burgerList){
            BurgerVO burger = new BurgerVO(b);
            if((Period.between(now, burger.getBurgerRegDt()).getMonths() == 0)&&(Period.between(now, burger.getBurgerRegDt()).getYears() == 0)){
                burger.setBurgerNew(true);
            }
            // else if(){
                //     burger.setBurgerBest(true);
                // }
                burgerResult.add(burger);
            }
            list.add(burgerResult);
            
            List<DrinkInfoEntity> drinkList = dRepo.findByCate(cate);
            List<DrinkVO> drinkresult = new ArrayList<>();
            for(DrinkInfoEntity d : drinkList){
                DrinkVO drink = new DrinkVO(d);
                drinkresult.add(drink);
            }
            list.add(drinkresult);
            
            List<DogInfoEntity> dogList = dogRepo.findByCate(cate);
            List<DogVO> dogresult = new ArrayList<>();
        for (DogInfoEntity dog : dogList) {
            DogVO Dog = new DogVO(dog);
            dogresult.add(Dog);
        }
        list.add(dogresult);
        
        List<SideInfoEntity> sideList = sRepo.findByCate(cate);
        List<SideVO> sideresult = new ArrayList<>();
        for (SideInfoEntity s : sideList) {
            SideVO side = new SideVO(s);
            sideresult.add(side);
        }
        list.add(sideresult);
        
        resultMap.put("list", list);
        resultMap.put("status", true);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }
    // public Map<String, Object> cateSeq(Long seq) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     LocalDate now = LocalDate.now();
    //     CategoryEntity cate = cateRepo.findByCateSeq(seq);
    //     if (cate==null) {
        //         resultMap.put("status", false);
        //         resultMap.put("message", "결과가 존재하지않습니다");
        //         resultMap.put("code", HttpStatus.NOT_FOUND);
    //         return resultMap;
    //     }
    //     // List<Object> list = new ArrayList<>();
    //     List<BurgerInfoEntity> burgerList = bRepo.findAllByOrderByBiSalesRateDesc();
    //     List<BurgerCateVo> result = new ArrayList<>();
    //     for(int i=0;i<burgerList.size();i++){
    //         BurgerCateVo b = new BurgerCateVo(burgerList.get(i), i+1);
    //         System.out.println(i);
    //         result.add(b);
    //     }

    //     // List<BurgerVO> burgerResult = new ArrayList<>();
    //     // List<BurgerCateVo> result = bRepo.searchBurgerName();
    //     // System.out.println(result);
    //     // for(BurgerInfoEntity b : burgerList){
    //     //     BurgerVO burger = new BurgerVO(b);
    //     //     // if((Period.between(now, burger.getBurgerRegDt()).getMonths() == 0)&&(Period.between(now, burger.getBurgerRegDt()).getYears() == 0)){
    //     //     //     burger.setBurgerNew(true);
    //     //     // }
    //     //     // else if(bRepo.searchBurgerName() <=10){
    //     //     //     burger.setBurgerBest(true);
    //     //     // }
    //     //     burgerResult.add(burger);
    //     // }
    //     // list.add(burgerResult);

    //     List<DrinkInfoEntity> drinkList = dRepo.findByCate(cate);
    //     List<DrinkVO> drinkresult = new ArrayList<>();
    //     for(DrinkInfoEntity d : drinkList){
    //         DrinkVO drink = new DrinkVO(d);
    //         drinkresult.add(drink);
    //     }
    //     // list.add(drinkresult);

    //     List<DogInfoEntity> dogList = dogRepo.findByCate(cate);
    //     List<DogVO> dogresult = new ArrayList<>();
    //     for (DogInfoEntity dog : dogList) {
    //         DogVO Dog = new DogVO(dog);
    //         dogresult.add(Dog);
    //     }
    //     // list.add(dogresult);

    //     List<SideInfoEntity> sideList = sRepo.findByCate(cate);
    //     List<SideVO> sideresult = new ArrayList<>();
    //     for (SideInfoEntity s : sideList) {
    //         SideVO side = new SideVO(s);
    //         sideresult.add(side);
    //     }
    //     // list.add(sideresult);

    //     resultMap.put("list", result);
    //     resultMap.put("status", true);
    //     resultMap.put("message", "조회하였습니다.");
    //     resultMap.put("code", HttpStatus.ACCEPTED);
    //     return resultMap;
    // }
}
