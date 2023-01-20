package com.green.firstproject.entity.menu.basicmenu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.vo.menu.DogAddVO;

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
@Table(name="dog_info")
public class DogInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="dog_seq") private Long dogSeq;
     @Column(name="dog_name") private String dogName;
     @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore
     @JoinColumn(name="dog_cate") private CategoryEntity cate;
     @Column(name="dog_detail") private String dogDetail;
     @Column(name="dog_file") private String dogFile;
     @Column(name="dog_uri") private String dogUri;

     public DogInfoEntity(DogAddVO data){
          this.dogName=data.getName();
          this.dogDetail=data.getDetail();
          this.dogFile=data.getFile();
          this.dogUri=data.getUri();
     }
     public void setCategory(CategoryEntity cate){
          this.cate = cate;
     }
}