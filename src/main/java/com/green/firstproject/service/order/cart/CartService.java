package com.green.firstproject.service.order.cart;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.entity.stock.DogStockEntity;
import com.green.firstproject.entity.stock.DrinkStockEntity;
import com.green.firstproject.entity.stock.EventStockEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;
import com.green.firstproject.entity.stock.SideStockEntity;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.repository.stock.BurgerStockRepository;
import com.green.firstproject.repository.stock.DogStockRepository;
import com.green.firstproject.repository.stock.DrinkStockRepository;
import com.green.firstproject.repository.stock.EventStockRepository;
import com.green.firstproject.repository.stock.IngredientsStockRepository;
import com.green.firstproject.repository.stock.SideStockRepository;
import com.green.firstproject.vo.order.OrderIngredientsVO;

@Service
public class CartService {

     private Long seq=1L;

     @Autowired MenuInfoRepository menuRepo;
     @Autowired EventInfoRepository eventRepo;
     @Autowired SideOptionRepository soRepo;
     @Autowired DrinkOptionRepository doRepo;
     @Autowired IngredientsInfoRepository iiRepo;
     @Autowired BurgerStockRepository bsRepo;
     @Autowired DogStockRepository dogsRepo;
     @Autowired DrinkStockRepository dsRepo;
     @Autowired SideStockRepository ssRepo;
     @Autowired IngredientsStockRepository isRepo;
     @Autowired EventStockRepository esRepo;
     @Autowired SideOptionRepository soptRepo;
     @Autowired DrinkOptionRepository doptRepo;
     
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
               map.put("status",  false);
               map.put("message",  "주문 메뉴가 잘못되었습니다.");
               map.put("code",  HttpStatus.BAD_REQUEST);
               return map;
          }else if(eventSeq!=null){
               EventInfoEntity event = eventRepo.findByEiSeq(eventSeq);
               cart= new CartDetail(seq, 1, event); //일단 기본 주문 수량 1로 고정시킴. 이후에 팀원들과 상의필요
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
               map.put("message",  event.getEiName()+"을/를 카트에 담았습니다.");
               return map;
               
          }else if(menuSeq!=null){
               MenuInfoEntity menu = menuRepo.findByMenuSeq(menuSeq);
               cart = new CartDetail(seq, 1, menu); //일단 기본 주문 수량 1로 고정시킴. 이후에 팀원들과 상의필요
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
               cart.setTotalPrice();
               map.put("message",  menu.getMenuName()+"을/를 카트에 담았습니다.");
               map.put("cart",  cart);
          }
          map.put("status",  true);
          map.put("code",  HttpStatus.ACCEPTED);
          seq++;
          return map;
     }

     // 매장 재고 검사
     public Map<String, Object> stockCheck(CartDetail c, StoreInfoEntity store){
          Map<String, Object> map = new LinkedHashMap<>();
          BurgerInfoEntity burger = c.getMenu().getBurger();
          DogInfoEntity dog = c.getMenu().getDog();
          DrinkInfoEntity drink = c.getMenu().getDrink();
          SideInfoEntity side = c.getMenu().getSide();
          EventInfoEntity event = c.getEvent();
          boolean check = true;
          String soldout = "";
          if(burger!=null){
               BurgerStockEntity bs = bsRepo.findByStoreAndBurger(store, burger);
               if(bs.getBsStock()<c.getOdCount()){
                    check = false;
                    soldout+=burger.getBiName()+", ";
               }
          }
          if(dog!=null){
               DogStockEntity dogstock = dogsRepo.findByStoreAndDog(store, dog);
               if(dogstock.getDogsStock()<c.getOdCount()){
                    check = false;
                    soldout+=dog.getDogName()+", ";
               }
          }
          if(drink!=null){
               DrinkStockEntity ds = dsRepo.findByStoreAndDrink(store, drink);
               if(ds.getDsStock()<c.getOdCount()){
                    check = false;
                    soldout+=drink.getDiName()+", ";
               }
          }
          if(side!=null){
               SideStockEntity ss = ssRepo.findByStoreAndSide(store, side);
               if(ss.getSsStock()<c.getOdCount()){
                    check = false;
                    soldout+=side.getSideName()+", ";
               }
          }
          if(event!=null){
               EventStockEntity es = esRepo.findByStoreAndEvent(store, event);
               if(es.getEsStock()<c.getOdCount()){
                    check = false;
                    soldout+=event.getEiName()+", ";
               }
          }
          for(IngredientsInfoEntity i : c.getIngredient()){
               IngredientsStockEntity ing = isRepo.findByStoreAndIngredient(store, i);
               if(ing.getIsStock()<c.getOdCount()){
                    check = false;
                    soldout+=i.getIiName()+", ";
               }
          }
          if(check){
               map.put("status", check);
               map.put("message", "해당 메뉴를 카트에 담았습니다.");
               map.put("code", HttpStatus.ACCEPTED);
          }else{
               map.put("status", check);
               map.put("message", soldout+"은/는 현재 품절된 메뉴입니다. 해당 메뉴를 제외하고 다시 주문해주세요");
               map.put("code", HttpStatus.BAD_REQUEST);
          }
          return map;
     }

     //장바구니 조회
     public Map<String, Object> showCart(List<CartDetail> cart){
          Map<String, Object> map = new LinkedHashMap<>();
          if(cart==null || cart.size()==0){
               map.put("status", false);
               map.put("message", "카트에 담긴 메뉴가 없습니다.");
               map.put("code", HttpStatus.ACCEPTED);
          }else{
               map.put("status", true);
               map.put("message", "카트를 조회했습니다.");
               map.put("code", HttpStatus.ACCEPTED);
               map.put("cart", cart);
          }
          
          return map;
     }
     
     //장바구니 수량 변경
     public Map<String, Object> cartCountChange(CartDetail cart, Long seq, int count){
          Map<String, Object> map = new LinkedHashMap<>();
          cart.setOdCount(count);
          map.put("status", true);
          map.put("message", "메뉴를 수정하였습니다.");
          map.put("code", HttpStatus.ACCEPTED);
          map.put("change", cart);
          
          return map;
     }
     //장바구니 옵션 변경
     public Map<String, Object> cartOptionChange(CartDetail cart ,Long side, Long drink, Long drink2, Long...ingredient){
          Map<String, Object> map = new LinkedHashMap<>();
          Boolean setMenu = cart.getMenu().getBurger()!=null && cart.getMenu().getSide()!=null && cart.getMenu().getDrink()!=null;
          if(setMenu){
               if(side!=null){
                    SideOptionEntity sideOpt = soptRepo.findBySoSeq(side);
                    cart.setSide(sideOpt);
               }
               if(drink!=null){
                    DrinkOptionEntity drinkOpt = doptRepo.findByDoSeq(drink);
                    cart.setDrink(drinkOpt);
               }
          }else if(cart.getEvent()!=null){
               if(side!=null){
                    SideOptionEntity sideOpt = soptRepo.findBySoSeq(side);
                    cart.setSide(sideOpt);
               }
               if(drink!=null){
                    DrinkOptionEntity drinkOpt = doptRepo.findByDoSeq(drink);
                    cart.setDrink(drinkOpt);
               }
               if(drink2!=null){
                    DrinkOptionEntity drinkOpt2 = doptRepo.findByDoSeq(drink2);
                    cart.setDrink(drinkOpt2);
               }
          }
          if(cart.getMenu().getMenuSelect()){
               Set<IngredientsInfoEntity> ing = new HashSet<>();
               if(ingredient.length!=0){
                    for(Long ingSeq : ingredient){
                         IngredientsInfoEntity i = iiRepo.findByIiSeq(ingSeq);
                         ing.add(i);
                         
                    }
               }
               cart.setIngredient(ing);
          }else{
               map.put("status", false);
               map.put("message", "옵션을 변경할 수 없는 메뉴입니다.");
               map.put("code", HttpStatus.BAD_REQUEST);
               return map;
          }
          map.put("status", true);
          map.put("message", "옵션을 변경하였습니다.");
          map.put("code", HttpStatus.ACCEPTED);
          return map;
     }
     //장바구니 메뉴 삭제
     public Map<String, Object> cartMenuDelete(List<CartDetail> carts ,Long seq){
          Map<String, Object> map = new LinkedHashMap<>();
          CartDetail cart = findCart(carts, seq);
          carts.remove(cart);
          map.put("status", true);
          map.put("message", "카트에서 메뉴를 삭제했습니다.");
          map.put("code", HttpStatus.BAD_REQUEST);
          return map;
     }
     //선택 장바구니 찾기
     public CartDetail findCart(List<CartDetail> carts ,Long seq){
          Map<String, Object> map = new LinkedHashMap<>();
          CartDetail cart = new CartDetail();
          for(CartDetail c : carts){
               if(c.getSeq() == seq){
                    return c;
               }
          }
          return null;
     }
     
}
