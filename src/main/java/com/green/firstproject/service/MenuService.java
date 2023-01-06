package com.green.firstproject.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;

@Service
public class MenuService {
    @Autowired CategoryRepository cateRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired SideInfoRepository sRepo;
    @Autowired DogInfoRepository dogRepo;
    public Map<String, Object> cateSeq(Long seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        CategoryEntity cate = cateRepo.findByCateSeq(seq);
        if (cate==null) {
            resultMap.put("status", false);
            resultMap.put("message", "결과가 존재하지않습니다");
            resultMap.put("code", HttpStatus.NOT_FOUND);
            return resultMap;
        }
        List<Object> list = new ArrayList<>();
        list.add(bRepo.findByCate(cate));
        list.add(sRepo.findByCate(cate));
        list.add(dRepo.findByCate(cate));
        list.add(dogRepo.findByCate(cate));
        resultMap.put("list", list);
        return resultMap;

    }
}
