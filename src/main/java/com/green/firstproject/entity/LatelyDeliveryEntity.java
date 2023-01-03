package com.green.firstproject.entity;

import java.time.LocalDate;

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
@Table(name="lately_delivery")
public class LatelyDeliveryEntity {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="ld_seq") private Long ldSeq;
     @Column(name="ld_mi_seq") private Long ldMiSeq;
     @Column(name="ld_address") private String ldAddress;
     @Column(name="ld_detail_address") private String ldDetailAddress;
     @Column(name="ld_del_date") private LocalDate ldDelDate;
}