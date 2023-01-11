package com.green.firstproject.entity.order.cart;

import java.util.LinkedHashSet;
import java.util.Set;

import com.green.firstproject.vo.menu.IngredientVo;
import com.green.firstproject.vo.menu.cart.CartDrinkInfoVO;
import com.green.firstproject.vo.menu.cart.CartIngredientVO;
import com.green.firstproject.vo.menu.cart.CartSideInfoVO;

import lombok.Data;
@Data
public class CartVo {
    private Long cartSeq;
    private Integer menuCount;
    private String menuName;
    private String eventName;
    private CartSideInfoVO side;
    private CartDrinkInfoVO drink;
    private CartDrinkInfoVO drink2;
    private Set<CartIngredientVO> ingredient; //중복 제거를 위해 set으로 변경함
    private int price;

    public CartVo(CartDetail cart){
        this.cartSeq=cart.getCartSeq();
        this.menuCount=cart.getMenuCount();
        this.ingredient = new LinkedHashSet<>();
        if(cart.getMenu()!=null){
            this.menuName=cart.getMenu().getMenuName();
        }
        if(cart.getEventMenu()!=null){
            this.eventName=cart.getEventMenu().getEiName();
        }
        if(cart.getSideOpt()!=null){
            this.side = new CartSideInfoVO(cart.getSideOpt(), cart.getMenu().getMenuSize());
        }
        if(cart.getDrinkOpt()!=null){
            this.drink= new CartDrinkInfoVO(cart.getDrinkOpt(), cart.getMenu().getMenuSize());
        }
        if(cart.getDrink2Opt()!=null){
            this.drink2= new CartDrinkInfoVO(cart.getDrink2Opt(), cart.getMenu().getMenuSize());
        }
        if(cart.getIngredient().size()!=0 || cart.getIngredient()!=null){
            for(IngredientVo i : cart.getIngredient()){
                CartIngredientVO iVo = new CartIngredientVO(i);
                this.ingredient.add(iVo);
            }
        }
        this.price = cart.getPrice() * this.menuCount;
    }
}
