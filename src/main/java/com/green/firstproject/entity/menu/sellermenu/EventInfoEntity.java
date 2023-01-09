package com.green.firstproject.entity.menu.sellermenu;
import java.time.LocalDate;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.vo.menu.HiaEventAddVO;

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
@Table(name="event_info")
public class EventInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="ei_seq") private Long eiSeq;
     @Column(name="ei_name") private String eiName;
     @Column(name="ei_start_dt") private LocalDate eiStartDt;
     @Column(name="ei_end_dt") private LocalDate eiEndDt;
     @Column(name="ei_price") private Integer eiPrice;
     @Column(name="ei_detail") private String eiDetail;
     @ManyToOne 
     @JoinColumn(name="ei_di_seq") private DrinkInfoEntity diSeq;
     @ManyToOne 
     @JoinColumn(name="ei_di2_seq") private DrinkInfoEntity di2Seq;
     //  @Column(name="ei_cate") private Long eiCate;
     @ManyToOne 
     @JoinColumn(name="ei_cate") private CategoryEntity cate;
     @Column(name="ei_file") private String eiFile;
     @Column(name="ei_uri") private String eiUri;

     public EventInfoEntity(HiaEventAddVO data){
        this.eiName=data.getName();
        this.eiStartDt=data.getStartDt();
        this.eiEndDt=data.getEndDt();
        this.eiPrice=data.getPrice();
        this.eiDetail=data.getDetail();
        this.eiFile=data.getFile();
        this.eiUri=data.getUri();
     }

     public void setCategory(CategoryEntity cate){
        this.cate = cate;
     }

     public void setDrink(DrinkInfoEntity drink){
        this.diSeq = drink;
        this.di2Seq = drink;
     }
}