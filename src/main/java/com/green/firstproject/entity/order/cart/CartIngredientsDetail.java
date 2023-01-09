package com.green.firstproject.entity.order.cart;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartIngredientsDetail {
     private CartDetail cartDetail;
     private List<IngredientsInfoEntity> ingredient = new ArrayList<>();

     
}
