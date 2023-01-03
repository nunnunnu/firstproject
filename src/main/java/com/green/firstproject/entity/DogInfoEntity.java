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
@Table(name="dog_info")
public class DogInfoEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="dog_seq") private Long dogSeq;
     @Column(name="dog_name") private String dogName;
     @Column(name="dog_cate") private String dogCate;
     @Column(name="dog_detail") private String dogDetail;
     @Column(name="di_file") private String diFile;
     @Column(name="di_uri") private String diUri;
}