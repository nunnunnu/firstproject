package com.green.firstproject.entity.order;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.master.CouponInfoEntity;
import com.green.firstproject.entity.master.PaymentInfoEntity;
import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;

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
@Table(name = "order_info")
@DynamicInsert
public class OrderInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oi_seq")                    private Long oiSeq;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "oi_mi_seq") @JsonIgnore private MemberInfoEntity member;
    @Column(name = "oi_order_time")      @ColumnDefault("CURRENT_TIMESTAMP")   
    private LocalDateTime oiOrderTime;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "oi_si_seq") @JsonIgnore private StoreInfoEntity store;
    @Column(name = "oi_status")          @ColumnDefault("1")   
    private Integer oiStatus;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "oi_pay_seq")@JsonIgnore private PaymentInfoEntity pay;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "oi_ci_seq") @JsonIgnore private CouponInfoEntity coupon;
    
}
