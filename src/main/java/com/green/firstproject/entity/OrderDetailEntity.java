package com.green.firstproject.entity;

import org.hibernate.annotations.ColumnDefault;
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
@Table(name="oder_detail")
@DynamicInsert
public class OrderDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="od_seq") private Long odSeq;
    @Column(name="od_oi_seq") private Long odOiSeq;
    @Column(name="od_bi_seq") private Long odBiSeq;
    @Column(name="od_ei_seq") private Long odEiSeq;
    @Column(name="od_count") @ColumnDefault("1")
     private Integer odCount;
}
