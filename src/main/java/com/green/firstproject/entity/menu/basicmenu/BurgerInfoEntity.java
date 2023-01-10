package com.green.firstproject.entity.menu.basicmenu;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.vo.menu.BurgerAddVO;

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
      @Column(name="bi_reg_dt") @ColumnDefault("CURRENT_TIMESTAMP") private LocalDate biRegDt;
      @Column(name="bi_sales_rate") @ColumnDefault("0") private Integer biSalesRate;

      public BurgerInfoEntity(BurgerAddVO data){
         this.biName= data.getName();
         this.biDetail=data.getDetail();
         this.biFile=data.getFile();
         this.biUri=data.getUri();
         this.biRegDt=data.getRegDt();
         this.biSalesRate=data.getSalesRate();
      }
      public void setCategory(CategoryEntity cate){
         this.cate = cate;
      }

      public void upSales(){
         int sales = biSalesRate+1;
         this.biSalesRate = sales;
      }
}