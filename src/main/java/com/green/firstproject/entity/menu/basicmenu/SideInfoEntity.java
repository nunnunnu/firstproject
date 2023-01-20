package com.green.firstproject.entity.menu.basicmenu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.vo.menu.SideAddVO;

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
@Builder
@Table(name="side_info")
public class SideInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="side_seq") private Long sideSeq;
     @Column(name="side_name") private String sideName;
     @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore
     @JoinColumn(name="side_cate") private CategoryEntity cate;
     @Column(name="side_detail") private String sideDetail;
     @Column(name="side_file") private String sideFile;
     @Column(name="side_uri") private String sideUri;

     public SideInfoEntity(SideAddVO data){
        this.sideName= data.getName();
        this.sideDetail=data.getDetail();
        this.sideFile=data.getFile();
        this.sideUri=data.getUri();
     }
     public void setCategory(CategoryEntity cate){
        this.cate = cate;
     }
}
