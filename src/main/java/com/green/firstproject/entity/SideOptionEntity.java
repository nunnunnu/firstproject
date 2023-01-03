package com.green.firstproject.entity;

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
@Table(name = "side_option")
@DynamicInsert
public class SideOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "so_seq") private Long soSeq;
    @Column(name = "so_name") private String soName;
    @Column(name = "so_price") private int soPrice;
    @Column(name = "so_size") private int soSize;
    @Column(name = "so_file") private String soFile;
    @Column(name = "so_uri") private String soUri;
}
