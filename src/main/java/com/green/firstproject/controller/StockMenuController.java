package com.green.firstproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.stock.SideStockEntity;
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
import com.green.firstproject.repository.stock.BurgerStockRepository;
import com.green.firstproject.repository.stock.DogStockRepository;
import com.green.firstproject.repository.stock.DrinkStockRepository;
import com.green.firstproject.repository.stock.EventStockRepository;
import com.green.firstproject.repository.stock.IngredientsStockRepository;
import com.green.firstproject.repository.stock.SideStockRepository;
import com.green.firstproject.service.menu.MenuInfoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/menu/stock")
public class StockMenuController {
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
    @Autowired BurgerStockRepository bsRepo;
    @Autowired SideStockRepository ssRepo;
    @Autowired DrinkStockRepository dsRepo;
    @Autowired DogStockRepository dogsRepo;
    @Autowired IngredientsStockRepository isRepo;
    @Autowired EventStockRepository esRepo;

    @GetMapping("/side")
    public String getSideStock(Model model){
        List<SideInfoEntity> sideStockList =new ArrayList<SideInfoEntity>();
        model.addAttribute("sideStockList", sRepo.findAll());
        return "/stock/sideStock";
    }
    @GetMapping("/burger")
    public String getBurgerStock(Model model){
        List<BurgerInfoEntity> burgerStockList =new ArrayList<BurgerInfoEntity>();
        model.addAttribute("burgerStockList", bRepo.findAll());
        return "/stock/burgerStock";
    }

    @GetMapping("/drink")
    public String getDrinkStock(Model model){
        List<DrinkInfoEntity> drinkStockList =new ArrayList<DrinkInfoEntity>();
        model.addAttribute("drinkStockList", dRepo.findAll());
        return "/stock/drinkStock";
    }
    
    @GetMapping("/dog")
    public String getdogStock(Model model){
        List<DogInfoEntity> dogStockList =new ArrayList<DogInfoEntity>();
        model.addAttribute("dogStockList", dogRepo.findAll());
        return "/stock/dogStock";
    }
    
    @GetMapping("/event")
    public String geteventStock(Model model){
        List<EventInfoEntity> eventStockList =new ArrayList<EventInfoEntity>();
        model.addAttribute("eventStockList", eventRepo.findAll());
        return "/stock/eventStock";
    }
    
    @GetMapping("/ingredient")
    public String getingredientStock(Model model){
        List<IngredientsInfoEntity> ingredientStockList =new ArrayList<IngredientsInfoEntity>();
        model.addAttribute("ingredientStockList", iiRepo.findAll());
        return "/stock/ingredientStock";
    }
}
