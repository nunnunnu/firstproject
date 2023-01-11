package com.green.firstproject.entity.member;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.master.CouponInfoEntity;

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
@Table(name="member_coupon")
@DynamicInsert
public class MemberCouponEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mc_seq") private Long mcSeq;
    // @Column(name="mc_mi_seq") private Long mcMiSeq;
    // @Column(name="mc_ci_seq") private Long mcCiSeq;
    @Column(name="mc_date") @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDate mcDate;
    @Column(name="mc_use")  @ColumnDefault("true")
    private Boolean mcUse;
    @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore @JoinColumn(name="mc_mi_seq") MemberInfoEntity member;
    @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore @JoinColumn(name="mc_ci_seq") CouponInfoEntity coupon;
}
