package com.green.firstproject.service.menu;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.vo.menu.BurgerAddVO;
import com.green.firstproject.vo.menu.DogAddVO;
import com.green.firstproject.vo.menu.DrinkAddVO;
import com.green.firstproject.vo.menu.EventAddVO;
import com.green.firstproject.vo.menu.IngredAddVO;
import com.green.firstproject.vo.menu.SideAddVO;

@Service
public class BurgerService {
    @Autowired BurgerInfoRepository bRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired SideInfoRepository sideRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired IngredientsInfoRepository iRepo;
    @Autowired EventInfoRepository eRepo;

    public Map<String,Object> addBurger(BurgerAddVO data){ 
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            BurgerInfoEntity entity = new BurgerInfoEntity(data);
            CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
            if(bRepo.countByBiName(entity.getBiName()) != 0){
                resultMap.put("status", false);
                resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
            else{
                entity.setCategory(cate);
                bRepo.save(entity);
                resultMap.put("status", true);
                resultMap.put("message", "버거 정보가 등록되었습니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
            }
        return resultMap;
        }

    public Map<String, Object> addSide(SideAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SideInfoEntity entity = new SideInfoEntity(data);
        CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
        if(sideRepo.countBySideName(entity.getSideName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            entity.setCategory(cate);
            sideRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "사이드 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
    }   

    public Map<String, Object> addDrink(DrinkAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DrinkInfoEntity entity = new DrinkInfoEntity(data);
        CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
        if(dRepo.countByDiName(entity.getDiName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            entity.setCategory(cate);
            dRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "음료 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
    }

    public Map<String, Object> addDog(DogAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DogInfoEntity entity = new DogInfoEntity(data);
        CategoryEntity cate = cateRepo.findByCateSeq(data.getCate());
        if(dogRepo.countByDogName(entity.getDogName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            entity.setCategory(cate);
            dogRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "독퍼 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
    }

    public Map<String, Object> addIngredients(IngredAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        IngredientsInfoEntity entity = new IngredientsInfoEntity(data);
        if(iRepo.countByIiName(entity.getIiName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            iRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "재료 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
    }

    public Map<String, Object> addEvent(EventAddVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        EventInfoEntity entity = new EventInfoEntity(data);
        if(eRepo.countByEiName(entity.getEiName()) != 0){
            resultMap.put("status", false);
            resultMap.put("message", data.getName()+" 은/는 이미 등록된 메뉴입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            CategoryEntity c = cateRepo.findByCateSeq(data.getCate());
            DrinkInfoEntity d1 = dRepo.findByDiSeq(data.getDiSeq());
            DrinkInfoEntity d2 = dRepo.findByDiSeq(data.getDiSeq());
            entity.setCate(c);
            entity.setEiDiSeq(d1);
            entity.setEiDi2Seq(d2);
            eRepo.save(entity);
            resultMap.put("status", true);
            resultMap.put("message", "이벤트 메뉴 정보가 등록되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
    }
}
