package com.green.firstproject.entity;

import java.time.LocalDateTime;

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
@Table(name = "menu_info")
@DynamicInsert
public class MenuInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_seq")              private Long menuSeq;
    @Column(name = "menu_name")             private String menuName;
    @Column(name = "menu_price")            private Integer menuPrice;
    @Column(name = "menu_file")             private String menuFile;
    @Column(name = "menu_uri")              private String menuUri;
    @Column(name = "menu_reg_dt")     @ColumnDefault("CURRENT_TIMESTAMP")      
    private LocalDateTime menuRegDt;
    @Column(name = "menu_sales_rate") @ColumnDefault("0")      
    private Integer menuSalesRate;
    @Column(name = "menu_ex")               private String menuEx;
    @Column(name = "menu_bi_seq")           private Integer menuBiSeq;
    @Column(name = "menu_side_seq")         private Integer menuSideSeq;
    @Column(name = "menu_di_seq")           private Integer menuDiSeq;
    @Column(name = "menu_dog_seq")          private Integer menuDogSeq;
    @Column(name = "menu_size")             private Integer menuSize;
    @Column(name = "menu_select")     @ColumnDefault("false")      
    private Boolean menuSelect;
}
