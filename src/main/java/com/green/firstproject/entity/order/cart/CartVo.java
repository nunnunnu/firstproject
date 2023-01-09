package com.green.firstproject.entity.order.cart;

import java.util.Set;

import com.green.firstproject.vo.menu.IngredientVo;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class CartVo {
    private Long seq;
    private Integer odCount;
    private String menu;
    private String event;
    private String side;
    private String drink;
    private String drink2;
    private Set<String> ingredient; //중복 제거를 위해 set으로 변경함
    private int price;

    public CartVo(CartDetail cart){
        this.seq=cart.getSeq();
        this.odCount=cart.getOdCount();
        if(cart.getMenu()!=null){
            this.menu=cart.getMenu().getMenuName();
        }
        if(cart.getEvent()!=null){
            this.event=cart.getEvent().getEiName();
        }
        if(cart.getSide()!=null){
            this.side=cart.getSide().getSoName();
        }
        if(cart.getDrink()!=null){
            this.drink=cart.getDrink().getDoName();
        }
        if(cart.getDrink2()!=null){
            this.drink2=cart.getDrink2().getDoName();
        }
        if(cart.getIngredient().size()!=0 || cart.getIngredient()!=null){
            for(IngredientVo i : cart.getIngredient()){
                this.ingredient.add(i.getIngredientName());
            }
        }
        this.price = cart.getPrice();
    }
}
