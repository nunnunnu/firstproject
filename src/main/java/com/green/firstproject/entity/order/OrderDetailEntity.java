package com.green.firstproject.entity.order;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="order_detail")
@DynamicInsert
public class OrderDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="od_seq")              private Long odSeq ;
    @Column(name="od_count")                           private Integer odCount;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="od_oi_seq")           private OrderInfoEntity odOiseq;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="od_bi_seq")           private MenuInfoEntity odBiseq;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="od_ei_seq")           private EventInfoEntity odEiSeq;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="od_lsot_seq")         private SideOptionEntity odLsotSeq;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="od_ldot_seq")         private DrinkOptionEntity odLdotSeq;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="od_ldot2_seq")        private DrinkOptionEntity odLdot2Seq;

    public OrderDetailEntity(CartDetail cart){
        this.odCount=cart.getOdCount();
        this.odBiseq=cart.getMenu();
        this.odEiSeq=cart.getEvent();
        this.odLsotSeq=cart.getSide();
        this.odLdotSeq=cart.getDrink();
        this.odLdot2Seq=cart.getDrink2();
        
    }
}
