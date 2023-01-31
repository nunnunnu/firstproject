package com.green.firstproject.service.category;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.vo.menu.BurgerCateVo;
import com.green.firstproject.vo.menu.DogCateVO;
import com.green.firstproject.vo.menu.DrinkCateVO;
import com.green.firstproject.vo.menu.EventCateVO;
import com.green.firstproject.vo.menu.SideCateVO;

@Service
public class MenuService {
    @Autowired CategoryRepository cateRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired SideInfoRepository sRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired EventInfoRepository eRepo;
    @Autowired StoreInfoRepository siRepo;

    public Map<String, Object> cateSeq(Long seq, Long store) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        CategoryEntity cate = cateRepo.findByCateSeq(seq);
        if (cate==null) {
                resultMap.put("status", false);
                resultMap.put("message", "결과가 존재하지않습니다");
                resultMap.put("code", HttpStatus.NOT_FOUND);
            return resultMap;
        }

        // StoreInfoEntity store = siRepo.findAll().get(0);

        Map<String, Object> list = new LinkedHashMap<>();
        if(seq==2||seq==3||seq==4||seq==5){
            List<BurgerCateVo> burgerList = bRepo.searchBurger(seq, store);
            if(burgerList.size()!=0){
                list.put("burger", burgerList);
            }
        }else if(seq==7){
            List<DrinkCateVO> drinkList = dRepo.searchDrink(seq, store);
            if(drinkList.size()!=0){
                list.put("drink", drinkList);
            }
        }else if(seq==8){
            List<DogCateVO> dogList = dogRepo.searchDog(seq, store);
            System.out.println(dogList.size());
            if(dogList.size()!=0){
                list.put("dog", dogList);
            }
        }else if(seq==6){
            List<SideCateVO> sideList = sRepo.searchSide(seq, store);
            if(sideList.size()!=0){
                list.put("side", sideList);
            }
        }else if(seq==1){
            List<EventCateVO> eventList = eRepo.searchEvent(seq, store);
            if(eventList.size()!=0){
                list.put("event", eventList);
            }
        }

        if (list.size()==0) {
            resultMap.put("status", false);
            resultMap.put("message", "결과가 존재하지않습니다");
            resultMap.put("code", HttpStatus.NOT_FOUND);
            return resultMap;
        } 

        resultMap.put("list", list);
        resultMap.put("status", true);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }

    //MenuInfoService랑 합칠것
    
}
