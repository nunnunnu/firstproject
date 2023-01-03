package com.green.firstproject.entity.member;

import java.time.LocalDate;


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
@Table(name="member_info")
@DynamicInsert
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq") private Long miSeq;
    @Column(name="mi_email") private String miEmail;
    @Column(name="mi_pwd") private String miPwd;
    @Column(name="mi_name") private String miName;
    @Column(name="mi_phone") private String miPhone;
    @Column(name="mi_gen")  @ColumnDefault("0")
    private Integer miGen;
    @Column(name="mi_birth") private LocalDate miBirth;
    // @Column(name="mi_grade") private Integer miGrade;
    @Column(name="mi_status") @ColumnDefault("1")
    private Integer miStatus;
    @ManyToOne @JoinColumn(name="mi_grade") @ColumnDefault("1") GradeInfoEntity Grade;
}
