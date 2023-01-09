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
    private Long seq;
    private Integer odCount;
    private String menu;
    private String event;
    private CartSideInfoVO side;
    private CartDrinkInfoVO drink;
    private CartDrinkInfoVO drink2;
    private Set<CartIngredientVO> ingredient; //중복 제거를 위해 set으로 변경함
    private int price;

    public CartVo(CartDetail cart){
        this.seq=cart.getSeq();
        this.odCount=cart.getOdCount();
        this.ingredient = new LinkedHashSet<>();
        if(cart.getMenu()!=null){
            this.menu=cart.getMenu().getMenuName();
        }
        if(cart.getEvent()!=null){
            this.event=cart.getEvent().getEiName();
        }
        if(cart.getSide()!=null){
            this.side = new CartSideInfoVO(cart.getSide(), cart.getMenu().getMenuSize());
        }
        if(cart.getDrink()!=null){
            this.drink= new CartDrinkInfoVO(cart.getDrink(), cart.getMenu().getMenuSize());
        }
        if(cart.getDrink2()!=null){
            this.drink2= new CartDrinkInfoVO(cart.getDrink2(), cart.getMenu().getMenuSize());
        }
        if(cart.getIngredient().size()!=0 || cart.getIngredient()!=null){
            for(IngredientVo i : cart.getIngredient()){
                CartIngredientVO iVo = new CartIngredientVO(i);
                this.ingredient.add(iVo);
            }
        }
        this.price = cart.getPrice() * this.odCount;
    }
}
