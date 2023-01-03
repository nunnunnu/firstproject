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
@Table(name="my_delivery")
public class MyDeliveryEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="md_seq") private Long mdSeq;
     @Column(name="md_mi_seq") private String mdMiSeq;
     @Column(name="md_address") private String mdAddress;
     @Column(name="md_detail_address") private String mdDetailAddress;
     @Column(name="md_name") private String mdName;
}