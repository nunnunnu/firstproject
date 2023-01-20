package com.green.firstproject.entity.menu.basicmenu;
import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.vo.menu.DrinkAddVO;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="drink_info")
@Builder
public class DrinkInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="di_seq") private Long diSeq;
     @Column(name="di_name") private String diName;
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="di_cate") private CategoryEntity cate;
     @Column(name="di_detail") private String diDetail;
     @Column(name="di_file") private String diFile;
     @Column(name="di_uri") private String diUri;

     public DrinkInfoEntity(DrinkAddVO data){
        this.diName= data.getName();
        this.diDetail=data.getDetail();
        this.diFile=data.getDiFile();
        this.diUri=data.getDiFile();
     }
     public void setCategory(CategoryEntity cate){
        this.cate = cate;
     }
}
