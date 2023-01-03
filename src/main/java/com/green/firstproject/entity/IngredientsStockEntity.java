package com.green.firstproject.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    
    @Column(name = "is_seq")           private Long isSeq;
    @Column(name = "is_si_seq")        private Long isSiSeq;
    @Column(name = "is_ii_seq")        private Long isIiSeq;
    @Column(name = "is_stock")   @ColumnDefault("0")           
    private Integer isStock;
}
