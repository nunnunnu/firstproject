package com.green.firstproject.entity.master;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.member.GradeInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="coupon_info")
@DynamicInsert
public class CouponInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ci_seq") private Long ciSeq; 
    @Column(name="ci_name") private String ciName;
    @Column(name="ci_discount") private Double ciDiscount;
    // @Column(name="ci_gi_seq") @ColumnDefault("1")
    // private Long ciGiSeq;
    @OneToOne @JoinColumn(name="ci_gi_seq") 
    @ColumnDefault("1") GradeInfoEntity grade;
}
