package com.green.firstproject.entity.stock;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredients_stock")
@DynamicInsert
public class IngredientsStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "is_seq")           private Long isSeq;
    // @Column(name = "is_si_seq")        private Long isSiSeq;
    @ManyToOne @JoinColumn(name = "is_si_seq") StoreInfoEntity store;  
    // @Column(name = "is_ii_seq")        private Long isIiSeq;
    @OneToOne @JoinColumn(name = "is_ii_seq") IngredientsInfoEntity ingredient;
    @Column(name = "is_stock")   @ColumnDefault("0")           
    private Integer isStock;
}
