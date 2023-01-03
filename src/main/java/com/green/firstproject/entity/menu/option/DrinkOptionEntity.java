package com.green.firstproject.entity.menu.option;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "drink_option")
@DynamicInsert
public class DrinkOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_seq") private Long doSeq;
    @Column(name = "do_name") private String doName;
    @Column(name = "do_price") private int doPrice;
    @Column(name = "do_size") private int doSize;
    @Column(name = "do_file") private String doFile;
    @Column(name = "do_uri") private String doUri;
}
