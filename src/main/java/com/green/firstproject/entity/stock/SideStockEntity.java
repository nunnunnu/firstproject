package com.green.firstproject.entity.stock;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;

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
@Table(name = "side_stock")
@DynamicInsert
public class SideStockEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ss_seq") private Long ssSeq;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "ss_si_seq") private StoreInfoEntity store;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "ss_side_seq") private SideInfoEntity side;
    @Column(name = "ss_stock") @ColumnDefault("0") 
    private int ssStock;
    
}
