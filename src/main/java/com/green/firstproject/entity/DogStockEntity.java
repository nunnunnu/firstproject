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
@Table(name = "dog_stock")
@DynamicInsert

public class DogStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dogs_seq") private Long dogsSeq;
    @Column(name = "dogs_si_seq") private Long dogsSiSeq;
    @Column(name = "dogs_dog_seq") private Long dogsDogSeq;
    @Column(name = "dogs_stock") @ColumnDefault("0")
    private int dogsStock;
    
}
