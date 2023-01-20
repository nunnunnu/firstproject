package com.green.firstproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.vo.add.SideAddVO;
import com.green.firstproject.vo.menu.BurgerAddVO;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/menu/add")
public class AddMenuController {
    @Autowired SideInfoRepository sRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired BurgerInfoRepository bRepo;

    @GetMapping("/side")
    public String getsideAdd(Model model) {
        model.addAttribute("sideList", sRepo.findAll());
        model.addAttribute("categoryList", cateRepo.findAll());
        return "/side";
    }
    
    @PostMapping("/side")
    public String postsideAdd(SideAddVO data) {
        SideInfoEntity entity = SideInfoEntity.builder()
        .sideName(data.getSideTitle()).sideDetail(data.getSideDetail())
        .cate(cateRepo.findByCateSeq(data.getCategory()))
        .sideFile(data.getSideFile()).sideUri(data.getSideUri())
        .build();
        sRepo.save(entity);
        return "redirect:/menu/add/side";
    }
    @GetMapping("/burger")
    public String getBurgerAdd(Model model) {
        model.addAttribute("burgerList", bRepo.findAll());
        model.addAttribute("categoryList", cateRepo.findAll());
        return "/burger";
    }

    @PostMapping("/burger")
    public String postburgerAdd(BurgerAddVO data) {
        BurgerInfoEntity entity = BurgerInfoEntity.builder()
                .biName(data.getName()).biDetail(data.getDetail())
                .cate(cateRepo.findByCateSeq(data.getCate()))
                .biFile(data.getFile()).biUri(data.getUri())
                .biRegDt(data.getRegDt()).build();
        bRepo.save(entity);
        return "redirect:/menu/add/burger";
    }
    
}