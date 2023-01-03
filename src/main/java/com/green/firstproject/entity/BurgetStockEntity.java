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
@Table(name = "burger_stock")
@DynamicInsert
public class BurgetStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bs_seq") private Long bsSeq;
    @Column(name = "bs_si_seq") private Long bsSiSeq;
    @Column(name = "bs_bi_seq") private Long bsBiSeq;
    @Column(name = "bs_stock") @ColumnDefault("0") 
    private int bsStock;
}
