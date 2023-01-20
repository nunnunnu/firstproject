package com.green.firstproject.entity.order.cart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {

     // private Long cartSeq;
     private Integer count;
     // private OrderInfoEntity order;
     private Long menu;
     private Long eventMenu;
     private Long sideOpt;
     private Long drinkOpt;
     private Long drink2Opt; //싹다 VO로 변경
     private List<Long> ingredient; //중복 제거를 위해 set으로 변경함
     private int price;

     // public CartDetail(Long seq, Integer count, MenuInfoEntity menu){
     //      // this.cartSeq = seq;
     //      this.menuCount=count;
     //      this.menu=menu;
     //      this.ingredient = new HashSet<>();
     // }
     // public CartDetail(Long seq, Integer count, EventInfoEntity event){
     //      // this.cartSeq = seq;
     //      this.menuCount=count;
     //      this.eventMenu=event;
     //      this.ingredient = new HashSet<>();
     // }

     // public void addIngredient(IngredientsInfoEntity ingrEntities){
     //      IngredientVo ing = new IngredientVo(ingrEntities);
     //      this.ingredient.add(ing);
     // }
     // public void changeIngredient(Set<IngredientsInfoEntity> ingrEntities){
     //      Set<IngredientVo> ingSet = new LinkedHashSet<>();
     //      for(IngredientsInfoEntity i : ingrEntities){
     //           IngredientVo ing = new IngredientVo(i);
     //           ingSet.add(ing);
     //      }
     //      ingredient = ingSet;
     // }
     // public void setTotalPrice(){
     //      Integer rSizeSidePrice=2700;
     //      Integer lSizeSidePrice=3200;
     //      Integer rSizeDrinkPrice = 2600;
     //      Integer lSizeDrinkPrice = 2800; //나중에 클래스 만들어야함
     //      if(menu!=null){
     //           this.price = menu.getMenuPrice();
     //      }else if(eventMenu!=null){
     //           this.price = eventMenu.getEiPrice();
     //      }
     //      if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
     //           if(sideOpt!=null){
     //                price += sideOpt.getSoPrice()-(menu.getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
     //           }
     //           if(drinkOpt!=null){
     //                price += drinkOpt.getDoPrice() - (menu.getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
     //           }
     //      }else if(eventMenu !=null){
     //           if(sideOpt!=null){
     //                price += sideOpt.getSoPrice()-(menu.getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
     //           }
     //           if(drinkOpt!=null){
     //                price += drinkOpt.getDoPrice() - (menu.getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
     //           }
     //           if(drink2Opt!=null){
     //                price += drink2Opt.getDoPrice() - (menu.getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
     //           }
     //      }
          
     //      int count = 0;
     //      if(menu.getMenuSelect()){
     //           for(IngredientVo i : ingredient){
     //                if(i.getIngredientPrice()==0){
     //                     count++;
     //                }else{
     //                     price+= i.getIngredientPrice();
     //                }
     //           }
     //      }
     //      if(count>1){
     //           price+=400;
     //      }
     // }
     // public void ingredientFreeMenu(){
     //      int count = 0;
     //      for(IngredientVo i : ingredient){
     //           if(i.getIngredientPrice()==0){
     //                count++;
     //           }
     //      }
     //      if(count>1){
     //           ingredient.add(new IngredientVo("재료 추가", 400));
     //      }
     // }
}
