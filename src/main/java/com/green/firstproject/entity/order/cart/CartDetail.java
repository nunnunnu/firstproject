package com.green.firstproject.entity.order.cart;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.vo.menu.IngredientVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {

     private Long seq;
     private Integer odCount;
     // private OrderInfoEntity order;
     private MenuInfoEntity menu;
     private EventInfoEntity event;
     private SideOptionEntity side;
     private DrinkOptionEntity drink;
     private DrinkOptionEntity drink2;
     private Set<IngredientVo> ingredient; //중복 제거를 위해 set으로 변경함
     private int price;

     public CartDetail(Long seq, Integer count, MenuInfoEntity menu){
          this.seq = seq;
          this.odCount=count;
          this.menu=menu;
          this.ingredient = new HashSet<>();
     }
     public CartDetail(Long seq, Integer count, EventInfoEntity event){
          this.seq = seq;
          this.odCount=count;
          this.event=event;
          this.ingredient = new HashSet<>();
     }

     public void addIngredient(IngredientsInfoEntity ingrEntities){
          IngredientVo ing = new IngredientVo(ingrEntities);
          this.ingredient.add(ing);
     }
     public void changeIngredient(Set<IngredientsInfoEntity> ingrEntities){
          Set<IngredientVo> ingSet = new LinkedHashSet<>();
          for(IngredientsInfoEntity i : ingrEntities){
               IngredientVo ing = new IngredientVo(i);
               ingSet.add(ing);
          }
          ingredient = ingSet;
     }
     public void setTotalPrice(){
          Integer rSizeSidePrice=2700;
          Integer lSizeSidePrice=3200;
          Integer rSizeDrinkPrice = 2600;
          Integer lSizeDrinkPrice = 2800;
          if(menu!=null){
               this.price = menu.getMenuPrice();
          }else if(event!=null){
               this.price = event.getEiPrice();
          }
          if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
               if(side!=null){
                    price += side.getSoPrice()-(menu.getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(drink!=null){
                    price += drink.getDoPrice() - (menu.getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }else if(event !=null){
               if(side!=null){
                    price += side.getSoPrice()-(menu.getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(drink!=null){
                    price += drink.getDoPrice() - (menu.getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
               if(drink2!=null){
                    price += drink2.getDoPrice() - (menu.getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }
          
          int count = 0;
          if(menu.getMenuSelect()){
               for(IngredientVo i : ingredient){
                    if(i.getIngredientPrice()==0){
                         if(count>1){
                              price+=400;
                         }
                         count++;
                    }else{
                         price+= i.getIngredientPrice();
                    }
               }
          }
     }
}
