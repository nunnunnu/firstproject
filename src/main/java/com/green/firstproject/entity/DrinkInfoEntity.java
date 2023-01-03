package com.green.firstproject.entity;
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
@Table(name="drink_info")
public class DrinkInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="di_seq") private Long diSeq;
     @Column(name="di_name") private String diName;
     @Column(name="di_cate") private Long diCate;
     @Column(name="di_detail") private String diDetail;
     @Column(name="di_file") private String diFile;
     @Column(name="di_uri") private String diUri;
}
