package com.green.firstproject.service.order.cart;

import java.util.ArrayList;
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
import com.green.firstproject.entity.order.cart.CartVo;
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
import com.green.firstproject.vo.menu.IngredientVo;

@Service
public class CartService {

     private static Long seq=1L; //임의로 기본키 지정

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
          @Nullable Set<Long> ingredientsSeq,
          Integer count
     ){
          Map<String, Object> map = new LinkedHashMap<>();
          CartDetail cart;
          if((eventSeq!=null&&menuSeq!=null) || (eventSeq==null&&menuSeq==null)){ //메뉴와 이벤트메뉴 모두 선택되지않았거나 둘다 선택되었을경우 에러처리
               map.put("status",  false);
               map.put("message",  "주문 메뉴가 잘못되었습니다.");
               map.put("code",  HttpStatus.BAD_REQUEST);
               return map;
          }else if(eventSeq!=null){ 
               EventInfoEntity event = eventRepo.findByEventMenu(eventSeq);
               cart= new CartDetail(seq, count, event);
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
               MenuInfoEntity menu = menuRepo.findMenuSeq(menuSeq);
               cart = new CartDetail(seq, count, menu); 
               if(menu.getBurger()!=null && menu.getSide()!=null&&menu.getDrink()!=null){ //세트메뉴일경우
                    if(sideOptSeq!=null){
                         SideOptionEntity sideOpt = soRepo.findBySoSeq(sideOptSeq);
                         cart.setSide(sideOpt);
                    }
                    if(drinkOptSeq!=null){
                         DrinkOptionEntity drinkOpt = doRepo.findByDoSeq(drinkOptSeq);
                         cart.setDrink(drinkOpt);
                    }
               }
               if(menu.getMenuSelect() && ingredientsSeq!=null){ //재료선택이 가능한 세트메뉴 + 추가한 재료가 있을 경우
                    for(Long seq : ingredientsSeq){
                         cart.addIngredient(iiRepo.findByIiSeq(seq));
                    }
               }
               map.put("message",  menu.getMenuName()+"을/를 카트에 담았습니다.");
               map.put("cart",  cart);
          }
          map.put("status",  true);
          map.put("code",  HttpStatus.ACCEPTED);
          seq++; //임의로 지정한 기본키 늘려줌
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
                    soldout+=burger.getBiName();
               }
          }
          if(dog!=null){
               DogStockEntity dogstock = dogsRepo.findByStoreAndDog(store, dog);
               if(dogstock.getDogsStock()<c.getOdCount()){
                    if(!check){
                         soldout+=", ";
                    }
                    check = false;
                    soldout+=dog.getDogName();
               }
          }
          if(drink!=null){
               DrinkStockEntity ds = dsRepo.findByStoreAndDrink(store, drink);
               if(ds.getDsStock()<c.getOdCount()){
                    if(!check){
                         soldout+=", ";
                    }
                    check = false;
                    soldout+=drink.getDiName();
               }
          }
          if(side!=null){
               SideStockEntity ss = ssRepo.findByStoreAndSide(store, side);
               if(ss.getSsStock()<c.getOdCount()){
                    if(!check){
                         soldout+=", ";
                    }
                    check = false;
                    soldout+=side.getSideName();
               }
          }
          if(event!=null){
               EventStockEntity es = esRepo.findByStoreAndEvent(store, event);
               if(es.getEsStock()<c.getOdCount()){
                    if(!check){
                         soldout+=", ";
                    }
                    check = false;
                    soldout+=event.getEiName();
               }
          }
          for(IngredientVo i : c.getIngredient()){
               IngredientsInfoEntity ingredientsInfoEntity = iiRepo.findByIiSeq(i.getIngredirentSeq());
               IngredientsStockEntity ing = isRepo.findByStoreAndIngredient(store, ingredientsInfoEntity);
               if(ing.getIsStock()<c.getOdCount()){
                    if(!check){
                         soldout+=", ";
                    }
                    check = false;
                    soldout+=i.getIngredientName();
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
               map.put("code", HttpStatus.BAD_REQUEST);
          }else{
               List<CartVo> carts = new ArrayList<>();
               for(CartDetail c : cart){
                    c.setTotalPrice();
                    c.ingredientFreeMenu();
                    carts.add(new CartVo(c));
               }

               map.put("status", true);
               map.put("message", "카트를 조회했습니다.");
               map.put("code", HttpStatus.ACCEPTED);
               map.put("cart", carts);
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
          // map.put("change", cart);
          
          return map;
     }
     //장바구니 옵션 변경
     public Map<String, Object> cartOptionChange(CartDetail cart ,Long side, Long drink, Long drink2, Set<Long> ingredient){ 
          
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
               Set<IngredientVo> ing = new HashSet<>();
               if(ingredient.size()!=0){
                    for(Long ingSeq : ingredient){
                         IngredientsInfoEntity i = iiRepo.findByIiSeq(ingSeq);
                         ing.add(new IngredientVo(i));
                         
                    }
               }
               cart.setIngredient(ing);
          }else if(!cart.getMenu().getMenuSelect() && ingredient.size()!=0){ //재료 추가 불가능한 메뉴인데 재료추가를 시도했다면 에러처리
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
          map.put("code", HttpStatus.ACCEPTED);
          return map;
     }
     //선택 장바구니 찾기
     public CartDetail findCart(List<CartDetail> carts ,Long seq){
          for(CartDetail c : carts){
               if(c.getSeq() == seq){
                    return c;
               }
          }
          return null;
     }
     
}
