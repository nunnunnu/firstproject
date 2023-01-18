package com.green.firstproject.entity.stock;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;

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
@Table(name = "dog_stock")
@DynamicInsert

public class DogStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dogs_seq") private Long dogsSeq;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "dogs_si_seq") private StoreInfoEntity store;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "dogs_dog_seq") private DogInfoEntity dog;
    @Column(name = "dogs_stock") @ColumnDefault("0")
    private int dogsStock;
    
}
