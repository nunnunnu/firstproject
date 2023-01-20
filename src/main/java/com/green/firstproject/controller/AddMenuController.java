package com.green.firstproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.vo.add.SideAddVO;
import com.green.firstproject.vo.menu.BurgerAddVO;
import com.green.firstproject.vo.menu.DogAddVO;
import com.green.firstproject.vo.menu.DrinkAddVO;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/menu/add")
public class AddMenuController {
    @Autowired SideInfoRepository sRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired CategoryRepository cRepo;
    @Autowired DogInfoRepository dogRepo;

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
    



    @GetMapping("/drink")
    public String getDrinkAdd(Model model){
        model.addAttribute("drinkList", dRepo.findAll());
        model.addAttribute("categoryList", cRepo.findAll());
        return "/drink";
    }
    @PostMapping("/drink")
    public String postDrinkAdd(DrinkAddVO data){
        DrinkInfoEntity entity = DrinkInfoEntity.builder()
        .diName(data.getName())
        .diDetail(data.getDetail())
        .diFile(data.getDiFile())
        .diUri(data.getDiUri())
        .cate(cRepo.findByCateSeq(data.getCategory()))
        .build();
        dRepo.save(entity);
        return "redirect:/api/menu/add/drink";
    }
    @GetMapping("/dog")
    public String getDogAdd(Model model){
        model.addAttribute("dogList", dogRepo.findAll());
        model.addAttribute("categoryList", cRepo.findAll());
        return "/dog";
    }
    @PostMapping("/dog")
    public String postDogAdd(DogAddVO data){
        System.out.println(data);
        DogInfoEntity entity = DogInfoEntity.builder()
        .dogName(data.getName())
        .dogDetail(data.getDetail())
        .dogFile(data.getFile())
        .dogUri(data.getUri())
        .cate(cRepo.findByCateSeq(data.getCate()))
        .build();
        dogRepo.save(entity);
        return "redirect:/api/menu/add/dog";
    }
}
