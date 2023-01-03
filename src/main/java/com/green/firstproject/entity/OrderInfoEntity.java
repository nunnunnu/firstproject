package com.green.firstproject.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oi_seq")                private Long oiSeq;
    // @Column(name = "oi_mi_seq")             private Long oiMiSeq;
    @ManyToOne @JoinColumn(name = "oi_mi_seq") MenuInfoEntity menu;
    @Column(name = "oi_order_time")      @ColumnDefault("CURRENT_TIMESTAMP")   
    private LocalDateTime oiOrderTime;
    // @Column(name = "oi_si_seq")             private Long oiSiSeq;
    @ManyToOne @JoinColumn(name = "oi_si_seq") StoreInfoEntity store;
    @Column(name = "oi_status")          @ColumnDefault("1")   
    private Integer oiStatus;
    // @Column(name = "oi_pay_seq")            private Long oiPaySeq;
    @ManyToOne @JoinColumn(name = "oi_pay_seq") PaymentInfoEntity pay;
    // @Column(name = "oi_ci_seq")             private Long oiCiSeq;
    @ManyToOne @JoinColumn(name = "oi_ci_seq") CouponInfoEntity coupon;
}
