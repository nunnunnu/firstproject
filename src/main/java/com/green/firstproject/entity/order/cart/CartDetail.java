package com.green.firstproject.entity.order.cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

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
     private Set<IngredientsInfoEntity> ingredient; //중복 제거를 위해 set으로 변경함
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
          this.ingredient.add(ingrEntities);
     }
     public void changeIngredient(Set<IngredientsInfoEntity> ing){
          ingredient = ing;
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
               for(IngredientsInfoEntity i : ingredient){
                    if(i.getIiPrice()==0){
                         if(count>1){
                              price+=400;
                         }
                         count++;
                    }else{
                         price+= i.getIiPrice();
                    }
               }
          }
     }
}
