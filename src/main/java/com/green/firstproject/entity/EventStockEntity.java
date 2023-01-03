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
@Table(name = "event_stock")
@DynamicInsert
public class EventStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "es_seq") private Long esSeq;
    @Column(name = "es_si_seq") private Long esSiSeq;
    @Column(name = "es_ei_seq") private Long esEiSeq;
    @Column(name = "es_stock") @ColumnDefault("0")
    private int esStock;
    
}
