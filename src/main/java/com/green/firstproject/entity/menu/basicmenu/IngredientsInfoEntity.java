package com.green.firstproject.entity.menu.basicmenu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ingredients_info")
public class IngredientsInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ii_seq")     private Long iiSeq;
    @Column(name = "ii_name")    private String iiName;
    @Column(name = "ii_price")   private Integer iiPrice;
    @Column(name = "ii_file")    private String iiFile;
    @Column(name = "ii_uri")     private String iiUri;
    @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore @JoinColumn(name = "ii_menu_seq") MenuInfoEntity menu;

}