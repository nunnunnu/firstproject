package com.green.firstproject.entity.order.cart;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.vo.menu.cart.CartDrinkInfoVO;
import com.green.firstproject.vo.menu.cart.CartIngredientVO;
import com.green.firstproject.vo.menu.cart.CartSideInfoVO;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CartVo {
    // private Long cartSeq;
    private Integer menuCount;
    private String menuName;
    private String eventName;
    private CartSideInfoVO side;
    private CartDrinkInfoVO drink;
    private CartDrinkInfoVO drink2;
    private List<CartIngredientVO> ingredient; //중복 제거를 위해 set으로 변경함
    private int price;

    public CartVo(CartDetail cart, MenuInfoEntity menu,
        SideOptionEntity sideOpt, DrinkOptionEntity drinkOpt, DrinkOptionEntity drink2Opt,
        List<IngredientsInfoEntity> ingredients
    ){
        this.menuCount=cart.getCount();
        this.ingredient = new ArrayList<>();
        this.menuName=menu.getMenuName();
        this.price+=menu.getMenuPrice();
        System.out.println(price);
        if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
            if(cart.getSideOpt()!=null){
                this.side = new CartSideInfoVO(sideOpt, menu.getMenuSize());
                this.price+=side.getSidePrice();
                System.out.println(price);
            }
            if(cart.getDrinkOpt()!=null){
                this.drink= new CartDrinkInfoVO(drinkOpt, menu.getMenuSize());
                this.price+=drink.getDrinkPrice();
                System.out.println(price);
            }
        }else if(menu.getEvent()!=null){
            if(cart.getDrinkOpt()!=null){
                this.drink= new CartDrinkInfoVO(drinkOpt, menu.getMenuSize());
                this.price+=drink.getDrinkPrice();
                System.out.println(price);
            }
            if(cart.getDrink2Opt()!=null){
                this.drink2= new CartDrinkInfoVO(drink2Opt);
                this.price += drink2.getDrinkPrice();
                System.out.println(price);
            }
        }
        if(ingredients.size()!=0){
            checkIngredient(ingredients);
        }
        this.price = this.price * this.menuCount;
    }
    
    public void checkIngredient(List<IngredientsInfoEntity> ingredients){
        int count = 0;
        for(IngredientsInfoEntity i : ingredients){
            CartIngredientVO iVo = new CartIngredientVO(i);
            this.ingredient.add(iVo);
            if(i.getIiPrice()==0){
                count++;
            }else{
                this.price+=i.getIiPrice();
                System.out.println(price);
            }
        }
        if(count>1){
            this.price +=400;
            ingredient.add(new CartIngredientVO("재료 추가", 400));
        }

    }
}
