package com.green.firstproject.vo.order;

import java.util.LinkedHashSet;
import java.util.Set;

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
          if(orderDetail.getOdBiseq()!=null){
               this.menuName = orderDetail.getOdBiseq().getMenuName();
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
          Integer rSizeSidePrice=2700;
          Integer lSizeSidePrice=3200;
          Integer rSizeDrinkPrice = 2600;
          Integer lSizeDrinkPrice = 2800;
          if(orderDetail.getOdBiseq()!=null){
               this.price += orderDetail.getOdBiseq().getMenuPrice();
          }else if(orderDetail.getOdEiSeq()!=null){
               this.price += orderDetail.getOdEiSeq().getEiPrice();
          }
          if(orderDetail.getOdBiseq().getBurger()!=null && orderDetail.getOdBiseq().getSide()!=null && orderDetail.getOdBiseq().getDrink()!=null){
               if(orderDetail.getOdLsotSeq()!=null){
                    price += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
          }else if(orderDetail.getOdEiSeq() !=null){
               if(orderDetail.getOdLsotSeq()!=null){
                    price += orderDetail.getOdLsotSeq().getSoPrice()-(orderDetail.getOdBiseq().getMenuSize()==1?rSizeSidePrice:lSizeSidePrice) ;
               }
               if(orderDetail.getOdLdotSeq()!=null){
                    price += orderDetail.getOdLdotSeq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
               }
               if(orderDetail.getOdLdot2Seq()!=null){
                    price += orderDetail.getOdLdot2Seq().getDoPrice() - (orderDetail.getOdBiseq().getMenuSize()==1?rSizeDrinkPrice:lSizeDrinkPrice);
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
