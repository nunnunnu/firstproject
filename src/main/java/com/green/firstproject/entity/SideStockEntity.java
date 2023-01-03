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
@Table(name = "side_stock")
@DynamicInsert
public class SideStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ss_seq") private Long ssSeq;
    @Column(name = "ss_si_seq") private Long  ssSiSeq;
    @Column(name = "ss_side_seq") private Long ssSideSeq;
    @Column(name = "ss_stock") @ColumnDefault("0") 
    private int ssStock;
    
}
