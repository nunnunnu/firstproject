package com.green.firstproject.entity.menu.basicmenu;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.menu.CategoryEntity;

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
@DynamicInsert
@Table(name="burger_info")
public class BurgerInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="bi_seq") private Long biSeq;
     @Column(name="bi_name") private String biName;
     @ManyToOne
     @JoinColumn(name="bi_cate") private CategoryEntity cate;
     @Column(name="bi_detail") private String biDetail;
     @Column(name="bi_file") private String biFile;
     @Column(name="bi_uri") private String biUri;
     @Column(name="bi_reg_dt") @ColumnDefault("CURRENT_TIMESTAMP") private String biRegDt;
     @Column(name="bi_sales_rate") @ColumnDefault("0") private String biSalesRate;
}