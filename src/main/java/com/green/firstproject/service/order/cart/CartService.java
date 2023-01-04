package com.green.firstproject.service.order.cart;

import java.awt.Menu;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;

@Service
public class CartService {

     @Autowired MenuInfoRepository menuRepo;
     @Autowired EventInfoRepository eventRepo;
     @Autowired SideOptionRepository soRepo;
     @Autowired DrinkOptionRepository doRepo;
     @Autowired IngredientsInfoRepository iiRepo;
     
     public Map<String, Object> addCart(
           //멤버랑 스토어 로그인, 선택매장으로 바꿔야함
          @Nullable Long menuSeq,
          @Nullable Long eventSeq,
          @Nullable Long sideOptSeq,
          @Nullable Long drinkOptSeq,
          @Nullable Long drinkOpt2Seq,
          @Nullable Long[] ingredientsSeq
     ){
          Map<String, Object> map = new LinkedHashMap<>();
          
          CartDetail cart;
          if((eventSeq!=null&&menuSeq!=null) || (eventSeq==null&&menuSeq==null)){
               map.put("status", false);
               map.put("message", "주문 메뉴가 잘못되었습니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }else if(eventSeq!=null){
               EventInfoEntity event = eventRepo.findByEiSeq(eventSeq);
               cart= new CartDetail(1, event); //일단 기본 주문 수량 1로 고정시킴. 이후에 팀원들과 상의필요
               if(sideOptSeq!=null){
                    SideOptionEntity sideOpt = soRepo.findBySoSeq(sideOptSeq);
                    cart.setSide(sideOpt);
               }
               if(drinkOptSeq!=null){
                    DrinkOptionEntity drinkOpt = doRepo.findByDoSeq(drinkOptSeq);
                    cart.setDrink(drinkOpt);
               }
               if(drinkOpt2Seq!=null){
                    DrinkOptionEntity drinkOpt2 = doRepo.findByDoSeq(drinkOpt2Seq);
                    cart.setDrink2(drinkOpt2);
               }
               map.put("status", true);
               map.put("message", event.getEiName()+"을/를 장바구니에 담았습니다.");
               map.put("code", HttpStatus.ACCEPTED);
               map.put("cart", cart);
               return map;
               
          }else if(menuSeq!=null){
               MenuInfoEntity menu = menuRepo.findByMenuSeq(menuSeq);
               cart = new CartDetail(1, menu); //일단 기본 주문 수량 1로 고정시킴. 이후에 팀원들과 상의필요
               if(menu.getBurger()!=null && menu.getSide()!=null&&menu.getDrink()!=null){
                    if(sideOptSeq!=null){
                         SideOptionEntity sideOpt = soRepo.findBySoSeq(sideOptSeq);
                         cart.setSide(sideOpt);
                    }
                    if(drinkOptSeq!=null){
                         DrinkOptionEntity drinkOpt = doRepo.findByDoSeq(drinkOptSeq);
                         cart.setDrink(drinkOpt);
                    }
               }
               if(menu.getMenuSelect() && ingredientsSeq!=null){
                    for(Long seq : ingredientsSeq){
                         cart.addIngredient(iiRepo.findByIiSeq(seq));
                    }
                    
               }
               map.put("status", true);
               map.put("message", menu.getMenuName()+"을/를 장바구니에 담았습니다.");
               map.put("code", HttpStatus.ACCEPTED);
               map.put("cart", cart);
          }
          
          
          return map;
     }
}
