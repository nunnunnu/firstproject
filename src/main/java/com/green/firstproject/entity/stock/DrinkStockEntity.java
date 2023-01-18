package com.green.firstproject.entity.stock;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;

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
@Table(name = "drink_stock")
@DynamicInsert
public class DrinkStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ds_seq") private Long dsSeq;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "ds_si_seq") private StoreInfoEntity store;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "ds_di_seq") private DrinkInfoEntity drink;
    @Column(name = "ds_stock") @ColumnDefault("0")
    private int dsStock;
    
}
