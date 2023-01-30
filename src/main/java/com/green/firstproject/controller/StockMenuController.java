package com.green.firstproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.firstproject.entity.master.StoreInfoEntity;
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
import com.green.firstproject.service.stock.StockService;
import com.green.firstproject.vo.member.AdminInfoVO;
import com.green.firstproject.vo.stock.BurgerStockVO;

import jakarta.servlet.http.HttpSession;

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
    @Autowired StockService stockService;

    @GetMapping("/side")
    public String getSideStock(Model model, HttpSession session){
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        if(admin==null){
            return "redirect:/";
        }
        StoreInfoEntity store = siRepo.findBySiSeq(admin.getAdminStore());
        if(store==null){
            return "redirect:/";
        }
        
        List<BurgerStockVO> sideStockList = ssRepo.stockAll(store.getSiSeq());
        model.addAttribute("sideStockList", sideStockList);
        model.addAttribute("store", store);
        return "/stock/sideStock";
    }
    @GetMapping("/burger")
    public String getBurgerStock(Model model, HttpSession session){
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        if(admin==null){
            return "redirect:/";
        }
        StoreInfoEntity store = siRepo.findBySiSeq(admin.getAdminStore());
        if(store==null){
            return "redirect:/";
        }
        List<BurgerStockVO> burgerStockList = bsRepo.stockAll(store.getSiSeq());
        model.addAttribute("burgerStockList", burgerStockList);
        model.addAttribute("store", store);
        return "/stock/burgerStock";
    }

    @GetMapping("/drink")
    public String getDrinkStock(Model model, HttpSession session){
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        if(admin==null){
            return "redirect:/";
        }
        StoreInfoEntity store = siRepo.findBySiSeq(admin.getAdminStore());
        if(store==null){
            return "redirect:/";
        }
        List<BurgerStockVO> drinkStockList = dsRepo.stockAll(store.getSiSeq());
        model.addAttribute("drinkStockList", drinkStockList);
        model.addAttribute("store", store);
        return "/stock/drinkStock";
    }
    
    @GetMapping("/dog")
    public String getdogStock(Model model, HttpSession session){
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        if(admin==null){
            return "redirect:/";
        }
        StoreInfoEntity store = siRepo.findBySiSeq(admin.getAdminStore());
        if(store==null){
            return "redirect:/";
        }
        List<BurgerStockVO> dogStockList = dogsRepo.stockAll(store.getSiSeq());
        model.addAttribute("dogStockList", dogStockList);
        model.addAttribute("store", store);
        return "/stock/dogStock";
    }
    
    @GetMapping("/event")
    public String geteventStock(Model model, HttpSession session){
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        if(admin==null){
            return "redirect:/";
        }
        StoreInfoEntity store = siRepo.findBySiSeq(admin.getAdminStore());
        if(store==null){
            return "redirect:/";
        }
        List<BurgerStockVO> eventStockList = esRepo.stockAll(store.getSiSeq());
        model.addAttribute("eventStockList", eventStockList);
        model.addAttribute("store", store);
        return "/stock/eventStock";
    }
    
    @GetMapping("/ingredient")
    public String getingredientStock(Model model, HttpSession session){
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        if(admin==null){
            return "redirect:/";
        }
        StoreInfoEntity store = siRepo.findBySiSeq(admin.getAdminStore());
        if(store==null){
            return "redirect:/";
        }
        List<BurgerStockVO> ingredientStockList = isRepo.stockAll(store.getSiSeq());
        model.addAttribute("ingredientStockList", ingredientStockList);
        model.addAttribute("store", store);
        return "/stock/ingredientStock";
    }

    @GetMapping("/burger/update")
    public String getBurger(@RequestParam Long seq, @RequestParam Long store){
        stockService.updateBurgerStatus(seq, store);
        return "redirect:/menu/stock/burger";
    }
    @GetMapping("/dog/update")
    public String getDog(@RequestParam Long seq, @RequestParam Long store){
        stockService.updateDogStatus(seq, store);
        return "redirect:/menu/stock/dog";
    }
    @GetMapping("/drink/update")
    public String getDrink(@RequestParam Long seq, @RequestParam Long store){
        stockService.updateDrinkStatus(seq, store);
        return "redirect:/menu/stock/drink";
    }
    @GetMapping("/event/update")
    public String getEvent(@RequestParam Long seq, @RequestParam Long store){
        stockService.updateEventStatus(seq, store);
        return "redirect:/menu/stock/event";
    }
    @GetMapping("/ingredient/update")
    public String getIngredient(@RequestParam Long seq, @RequestParam Long store){
        stockService.updateIngredientStatus(seq, store);
        return "redirect:/menu/stock/ingredient";
    }
    @GetMapping("/side/update")
    public String getSide(@RequestParam Long seq, @RequestParam Long store){
        stockService.updateSideStatus(seq, store);
        return "redirect:/menu/stock/side";
    }
}
