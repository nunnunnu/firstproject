package com.green.firstproject.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(name="od_oi_seq") 
    private Long odOiSeq;
    @Column(name="od_bi_seq") private Long odBiSeq;
    @Column(name="od_ei_seq") private Long odEiSeq;
    @Column(name="od_count") @ColumnDefault("1")
    private Integer odCount;
    @ManyToOne @JoinColumn(name="od_oi_seq") OrderInfoEntity order;
    @ManyToOne @JoinColumn(name="od_bi_seq") MenuInfoEntity menu;
    @ManyToOne @JoinColumn(name="od_ei_seq") EventInfoEntity event;
}
