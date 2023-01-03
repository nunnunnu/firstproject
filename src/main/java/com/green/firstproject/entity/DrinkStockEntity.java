package com.green.firstproject.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drink_stock")
@DynamicInsert
public class DrinkStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ds_seq") private Long dsSeq;
    @Column(name = "ds_si_seq") private Long dsSiSeq;
    @Column(name = "ds_di_seq") private Long dsDiSeq;
    @Column(name = "ds_stock") @ColumnDefault("0")
    private int dsStock;
    
}
