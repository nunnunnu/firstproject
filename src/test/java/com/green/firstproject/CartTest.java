package com.green.firstproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.entity.order.cart.CartIngredientsDetail;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;

@SpringBootTest
public class CartTest {
     
     @Autowired MemberInfoReposiroty mRepo;
     @Autowired StoreInfoRepository siRepo;
     @Autowired MenuInfoRepository menuRepo;
     @Autowired EventInfoRepository eRepo;
     @Autowired SideOptionRepository soRepo;
     @Autowired DrinkOptionRepository diRepo;
     @Autowired IngredientsInfoRepository iiRepo;

     @Test
     void 일반메뉴주문(){
          MemberInfoEntity member = mRepo.findAll().get(0);
          StoreInfoEntity store = siRepo.findAll().get(0);
          MenuInfoEntity menu = menuRepo.findAll().get(0);
          CartDetail cart = new CartDetail(null, menu, null, null, null, null);
          
          if(menu.getBurger()!=null && menu.getDrink()!=null && menu.getSide()!=null){
               SideOptionEntity sideOption = soRepo.findAll().get(0);
               DrinkOptionEntity drinkOption = diRepo.findAll().get(0);
               cart.setSide(sideOption);
               cart.setDrink(drinkOption);
          }
          System.out.println(cart);
          
          List<IngredientsInfoEntity> ingredients = new ArrayList<>();
          // System.out.println(iiRepo.findAll());
          ingredients.add(iiRepo.findAll().get(0));
          ingredients.add(iiRepo.findAll().get(1));
          System.out.println(ingredients.size());
          CartIngredientsDetail cartingredients = new CartIngredientsDetail(cart, ingredients);
          // if(menu.getMenuSelect()){
          //      for(IngredientsInfoEntity ing : ingredients){
          //           cartingredients.getIngredient().add(ing);
          //      }
          // }
          System.out.println(cartingredients);
          

          

     }
}
