package com.green.firstproject.vo.order;

import java.util.LinkedHashSet;
import java.util.Set;

import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.vo.menu.IngredientVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVO {
     // private Long Seq ;
     // private OrderVO order;
     private String menuName;
     private String eventName;
     private Integer menuCount;
     private String sideOpt;
     private String drinkOpt;
     private String drinkOpt2;
     private Set<OrderIngredientsVO> ingredients;
     private int price;

     public OrderDetailVO(OrderDetailEntity orderDetail){
          ingredients = new LinkedHashSet<>();
          // this.Seq = orderDetail.getOdSeq();
          // this.order = new OrderVO(orderDetail.getOdOiseq());
          this.menuCount=orderDetail.getOdCount();

          MenuInfoEntity menu = orderDetail.getOdBiseq();
          if(menu!=null){
               this.menuName = menu.getMenuName();
          }
          if(orderDetail.getOdLsotSeq()!=null){
               this.sideOpt=orderDetail.getOdLsotSeq().getSoName();
          }
          if(orderDetail.getOdLdotSeq()!=null){
               this.drinkOpt=orderDetail.getOdLdotSeq().getDoName();
          }
          if(orderDetail.getOdLdot2Seq()!=null){
               this.drinkOpt2=orderDetail.getOdLdot2Seq().getDoName();
          }
          addPrice(orderDetail);
     }

     public void addOrderIngredients(Set<OrderIngredientsVO> ingredientsVOs){
          for(OrderIngredientsVO vo : ingredientsVOs){
               this.ingredients.add(vo);
               if(vo.getIngredient().getIngredientPrice()==0){
               }
          }
     }

     public void setDetailPrice(CartDetail cart){
          this.price = cart.getPrice();
     }

     public void addPrice(OrderDetailEntity orderDetail){
          MenuInfoEntity menu = orderDetail.getOdBiseq();
          this.price += menu.getMenuPrice();
          if(menu.getBurger()!=null && menu.getSide()!=null && menu.getDrink()!=null){
               if(orderDetail.getOdLsotSeq()!=null){
                    price += orderDetail.getOdLsotSeq().getSoPrice();
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice();
               }
          }else if(menu.getEvent()!=null){
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice();
               }
               if(orderDetail.getOdLdot2Seq()!=null){
                    price += orderDetail.getOdLdot2Seq().getDoPrice();
               }
          }
     }
     public void checkIngredientFreeMenu(){
          int count=0;
          for(OrderIngredientsVO i : ingredients){
               if(i.getIngredient().getIngredientPrice()==0){
                    count++;
               }
          }
          if(count>1){
               ingredients.add(new OrderIngredientsVO(new IngredientVo("재료 추가", 400)));
          }
     }


}
