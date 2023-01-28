package com.green.firstproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.service.menu.MenuInfoService;
import com.green.firstproject.vo.add.BurgerAddFileVO;
import com.green.firstproject.vo.add.DogAddFIleVO;
import com.green.firstproject.vo.add.DrinkAddFileVO;
import com.green.firstproject.vo.add.DrinkOptAddFileVo;
import com.green.firstproject.vo.add.EventAddFileVO;
import com.green.firstproject.vo.add.IngredientsAddFileVO;
import com.green.firstproject.vo.add.SideAddFileVO;
import com.green.firstproject.vo.add.SideOptAddFileVO;
import com.green.firstproject.vo.store.StoreAddForm;


@Controller
@RequestMapping("/menu/add")
public class AddMenuController {
    @Autowired SideInfoRepository sRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired BurgerInfoRepository bRepo;
    @Autowired DrinkInfoRepository dRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired StoreInfoRepository siRepo;
    @Autowired IngredientsInfoRepository iiRepo;
    @Autowired EventInfoRepository eventRepo;
    @Autowired DrinkOptionRepository doRepo;
    @Autowired SideOptionRepository soRepo;
    @Autowired MenuInfoService menuService;
    @Autowired MenuInfoRepository menuRepo;

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
        model.addAttribute("categoryList", cateRepo.findAll());
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
        model.addAttribute("categoryList", cateRepo.findAll());
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
        StoreInfoEntity store = new StoreInfoEntity(null, data.getName(), data.getAddress(), data.getDetailAddress(), data.getPhone(),
        data.getOpenTime(), data.getCloseTime(), data.getDeliveryPrice(), 1, data.getDeliveryArea());
        siRepo.save(store);
        return "redirect:/";
    }
    @GetMapping("/ingredients")
    public String getIngredient(Model model){
        model.addAttribute("ingredientsList", iiRepo.findAll());
        model.addAttribute("menu", menuRepo.findAll());
        return "/ingredients";
    }
    @PostMapping("/ingredients")
    public String postIngredientAdd(IngredientsAddFileVO data) {
        System.out.println("sss");
        menuService.saveIngredientFile(data);
        return "redirect:/menu/add/ingredients";
    }
    @GetMapping("/event")
    public String geteventAdd(Model model) {
        model.addAttribute("eventList", eventRepo.findAll());
        model.addAttribute("categoryList", cateRepo.findAll());
        return "/event";
    }
    @PostMapping("/event")
    public String posteventAdd(EventAddFileVO data) {
        menuService.saveEventFile(data);
        return "redirect:/menu/add/event";
    }
    @GetMapping("/drinkOpt")
    public String getdrinkOptAdd(Model model) {
        model.addAttribute("drinkOptList", doRepo.findAll());
        model.addAttribute("sizeList", doRepo.findAll());
        return "/drinkOpt";
    }
    @PostMapping("/drinkOpt")
    public String postdrinkOptAdd(DrinkOptAddFileVo data) {
        menuService.saveDrinkOptFile(data);
        return "redirect:/menu/add/drinkOpt";
    }
    @GetMapping("/sideOpt")
    public String getsideOptAdd(Model model) {
        model.addAttribute("sideOptList", soRepo.findAll());
        model.addAttribute("sizeList", soRepo.findAll());
        return "/sideOpt";
    }
    @PostMapping("/sideOpt")
    public String postsideOptAdd(SideOptAddFileVO data) {
        menuService.saveSideOptFile(data);
        return "redirect:/menu/add/sideOpt";
    }
}
