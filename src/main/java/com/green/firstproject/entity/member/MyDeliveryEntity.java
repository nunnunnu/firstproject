package com.green.firstproject.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
     @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore  @JoinColumn(name="md_mi_seq") private MemberInfoEntity member;
     @Column(name="md_address") private String mdAddress;
     @Column(name="md_detail_address") private String mdDetailAddress;
     @Column(name="md_name") private String mdName;
}