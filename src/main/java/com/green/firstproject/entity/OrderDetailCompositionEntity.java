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
@Table(name = "oder_detail_composition")
@DynamicInsert
public class OrderDetailCompositionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="odc_seq") private Long odcSeq;
    @Column(name="odc_od_seq") private Long odcOdSeq;
    @Column(name="odc_lsopt_seq") private Long odcLostSeq;
    @Column(name="odc_ldopt_seq") private Long odcLdoptSeq;
}
