package com.green.firstproject.service.category;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.vo.menu.BurgerCateVo;
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
    @Autowired EventInfoRepository eRepo;

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
        List<BurgerCateVo> burgerList = bRepo.searchBurgerName(seq);
        if(burgerList.size()!=0){
            list.add(burgerList);
        }

        List<DrinkInfoEntity> drinkList = dRepo.findByCate(cate);
        if(drinkList.size()!=0){
            List<DrinkVO> drinkresult = new ArrayList<>();
            for(DrinkInfoEntity d : drinkList){
                DrinkVO drink = new DrinkVO(d);
                drinkresult.add(drink);
            }
            list.add(drinkresult);
        }
        
        List<DogInfoEntity> dogList = dogRepo.findByCate(cate);
        if(dogList.size()!=0){
            List<DogVO> dogresult = new ArrayList<>();
            for (DogInfoEntity dog : dogList) {
                DogVO Dog = new DogVO(dog);
                dogresult.add(Dog);
            }
            list.add(dogresult);
        }

        List<SideInfoEntity> sideList = sRepo.findByCate(cate);
        if(sideList.size()!=0){
            List<SideVO> sideresult = new ArrayList<>();
            for (SideInfoEntity s : sideList) {
                SideVO side = new SideVO(s);
                sideresult.add(side);
            }
            list.add(sideresult);
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
}
