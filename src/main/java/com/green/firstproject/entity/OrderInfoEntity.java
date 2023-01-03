package com.green.firstproject.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_info")
@DynamicInsert
public class OrderInfoEntity {
    
    @Column(name = "oi_seq")                private Long oiSeq;
    @Column(name = "oi_mi_seq")             private Long oiMiSeq;
    @Column(name = "oi_order_time")      @ColumnDefault("CURRENT_TIMESTAMP")   
    private LocalDateTime oiOrderTime;
    @Column(name = "oi_si_seq")             private Long oiSiSeq;
    @Column(name = "oi_status")          @ColumnDefault("1")   
    private Integer oiStatus;
    @Column(name = "oi_pay_seq")            private Long oiPaySeq;
    @Column(name = "oi_ci_seq")             private Long oiCiSeq;
}
