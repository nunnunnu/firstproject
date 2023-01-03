package com.green.firstproject.entity;

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
@Table(name="side_info")
public class SideInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="side_seq") private Long sideSeq;
     @Column(name="side_name") private String sideName;
     @ManyToOne
     @JoinColumn(name="side_cate") private CategoryEntity cate;
     @Column(name="side_detail") private String sideDetail;
     @Column(name="side_file") private String sideFile;
     @Column(name="side_uri") private String sideUri;
}
