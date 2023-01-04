package com.green.firstproject.entity.order;

import java.util.List;

import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_ingredients_detail")
public class OrderIngredientsDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="odi_seq") private Long odiSeq;
    // @Column(name="odi_odc_seq") private Long odiOdcSeq;
    // @Column(name="odi_ii_seq") private Long odiIiSeq;
    @ManyToOne @JoinColumn(name="odi_odc_seq") private OrderDetailEntity orderdetail;
    @ManyToOne @JoinColumn(name="odi_ii_seq")  private IngredientsInfoEntity ingredient;
    
}
