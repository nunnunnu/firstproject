package com.green.firstproject.entity.stock;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "burger_stock")
@DynamicInsert
public class BurgetStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bs_seq") private Long bsSeq;
    @ManyToOne @JoinColumn(name = "bs_si_seq") private StoreInfoEntity store;
    @ManyToOne @JoinColumn(name = "bs_bi_seq") private BurgerInfoEntity burger;
    @Column(name = "bs_stock") @ColumnDefault("0") 
    private int bsStock;
}
