package com.green.firstproject.vo.order;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;
import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderIngredientsVO {
     private Long seq;
     private OrderDetailEntity orderDetail;
     private IngredientsInfoEntity ingredient;

     public OrderIngredientsVO(OrderIngredientsDetailEntity orderIngredients){
          this.seq = orderIngredients.getOdiSeq();
          this.orderDetail = orderIngredients.getOrderdetail();
          this.ingredient = orderIngredients.getIngredient();
     }
}
