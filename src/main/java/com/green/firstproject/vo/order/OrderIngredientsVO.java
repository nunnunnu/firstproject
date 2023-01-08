package com.green.firstproject.vo.order;

import com.green.firstproject.entity.order.OrderIngredientsDetailEntity;
import com.green.firstproject.vo.menu.IngredientVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderIngredientsVO {
     private Long seq;
     private IngredientVo ingredient;

     public OrderIngredientsVO(OrderIngredientsDetailEntity orderIngredients){
          this.seq = orderIngredients.getOdiSeq();
          IngredientVo ing = new IngredientVo(orderIngredients.getIngredient());
          this.ingredient = ing;
     }
}
