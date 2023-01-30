package com.green.firstproject.entity.member;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.master.StoreInfoEntity;

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
@Table(name = "admin_info")
@DynamicInsert
public class AdminInfoEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  
  @Column(name="admin_seq") private Long  adminSeq;
  @Column(name="admin_email") private String  adminEmail;
  @Column(name="admin_name") private String  adminName;
  @Column(name="admin_pwd") private String  adminPwd;
  @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore @JoinColumn(name="admin_grade") @ColumnDefault("99") GradeInfoEntity adminGrade;
  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "admin_si_seq") private StoreInfoEntity store; 
}
