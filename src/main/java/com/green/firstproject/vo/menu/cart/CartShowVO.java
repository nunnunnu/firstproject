package com.green.firstproject.vo.menu.cart;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.vo.menu.IngredientVo;
import com.green.firstproject.vo.menu.MenuListVO;
import com.green.firstproject.vo.menu.SellerMenuVO;
import com.green.firstproject.vo.menu.option.DrinkOptionVO;
import com.green.firstproject.vo.menu.option.SideOptionVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartShowVO {

     private Integer count;
     private SellerMenuVO menu;
     private List<IngredientVo> ingredient = new ArrayList<>();
     private int price;

     public CartShowVO(SellerMenuVO menu, Integer count){
          this.count=count;
          this.menu=menu;
     }


     public void addIngredient(IngredientVo ing){
          this.ingredient.add(ing);
     }
     public void changeIngredient(List<IngredientsInfoEntity> ingrEntities){
          List<IngredientVo> ingSet = new ArrayList<>();
          for(IngredientsInfoEntity i : ingrEntities){
               IngredientVo ing = new IngredientVo(i);
               ingSet.add(ing);
          }
          ingredient = ingSet;
     }
     public void setTotalPrice(MenuInfoEntity menu, SideOptionEntity side, DrinkOptionEntity drink, DrinkOptionEntity drink2, List<IngredientsInfoEntity> ingredient){
          Integer rSizeSidePrice=2700;
          Integer lSizeSidePrice=3200;
          Integer rSizeDrinkPrice = 2600;
          Integer lSizeDrinkPrice = 2800; //나중에 클래스 만들어야함

          if(menu!=null){
               this.price += menu.getMenuPrice();
          }
          if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
               if(side!=null){
                    price += side.getSoPrice()-(menu.getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(drink!=null){
                    price += drink.getDoPrice() - (menu.getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }else if(menu.getEvent() !=null){
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
                         count++;
                    }else{
                         price+= i.getIiPrice();
                    }
               }
          }
          if(count>1){
               price+=400;
          }
     }
     public void ingredientFreeMenu(List<IngredientsInfoEntity> ingredients){
          int count = 0;
          for(IngredientsInfoEntity i : ingredients){
               if(i.getIiPrice()==0){
                    count++;
               }
          }
          if(count>1){
               ingredient.add(new IngredientVo("재료 추가", 400));
          }
     }
}
