package com.green.firstproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.service.menu.MenuInfoService;
import com.green.firstproject.vo.add.BurgerAddFileVO;
import com.green.firstproject.vo.add.DogAddFIleVO;
import com.green.firstproject.vo.add.DrinkAddFileVO;
import com.green.firstproject.vo.add.SideAddFileVO;
import com.green.firstproject.vo.store.StoreAddForm;


@Controller
@RequestMapping("/menu/add")
public class AddMenuController {
    @Autowired SideInfoRepository sRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired CategoryRepository cRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired StoreInfoRepository siRepo;

    @Autowired MenuInfoService menuService;

    @GetMapping("/side")
    public String getsideAdd(Model model) {
        model.addAttribute("sideList", sRepo.findAll());
        model.addAttribute("categoryList", cateRepo.findAll());
        return "/side";
    }
    
    @PostMapping("/side")
    public String postsideAdd(SideAddFileVO data) {
        System.out.println(data.getSideFile().getOriginalFilename());
        menuService.saveFile(data);

        return "redirect:/menu/add/side";
    }
    @GetMapping("/burger")
    public String getBurgerAdd(Model model) {
        model.addAttribute("burgerList", bRepo.findAll());
        model.addAttribute("categoryList", cateRepo.findAll());
        return "/burger";
    }

    @PostMapping("/burger")
    public String postburgerAdd(BurgerAddFileVO data) {
        menuService.saveFile(data);
        return "redirect:/menu/add/burger";
    }
    
    @GetMapping("/drink")
    public String getDrinkAdd(Model model){
        model.addAttribute("drinkList", dRepo.findAll());
        model.addAttribute("categoryList", cRepo.findAll());
        return "/drink";
    }
    @PostMapping("/drink")
    public String postDrinkAdd(DrinkAddFileVO data){
        menuService.saveDrinkFile(data);
        return "redirect:/api/menu/add/drink";
    }
    @GetMapping("/dog")
    public String getDogAdd(Model model){
        model.addAttribute("dogList", dogRepo.findAll());
        model.addAttribute("categoryList", cRepo.findAll());
        return "/dog";
    }
    @PostMapping("/dog")
    public String postDogAdd(DogAddFIleVO data){
        menuService.saveDogFile(data);
        return "redirect:/api/menu/add/dog";
    }

    @GetMapping("/store")
    public String getStoreAdd(){
        
        return "/store";
    }
    @PostMapping("/store")
    public String postStoreAdd(StoreAddForm data){
        System.out.println(data);
        StoreInfoEntity store = new StoreInfoEntity(null, data.getName(), data.getAddress(), data.getDetailAddress(), data.getPhone(), data.getOpenTime(), data.getCloseTime(), data.getDeliveryPrice(), 1);
        siRepo.save(store);
        
        return "redirect:/";
    }

    
}
