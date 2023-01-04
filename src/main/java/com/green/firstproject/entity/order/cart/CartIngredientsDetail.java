package com.green.firstproject.entity.order.cart;

import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.order.OrderDetailEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartIngredientsDetail {
     private OrderDetailEntity orderdetail;
     private List<IngredientsInfoEntity> ingredient;
}
