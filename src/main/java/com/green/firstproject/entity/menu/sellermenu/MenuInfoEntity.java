package com.green.firstproject.entity.menu.sellermenu;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "menu_info")
@DynamicInsert
public class MenuInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_seq")              private Long menuSeq;
    @Column(name = "menu_name")             private String menuName;
    @Column(name = "menu_price")            private Integer menuPrice;
    @Column(name = "menu_file")             private String menuFile;
    @Column(name = "menu_uri")              private String menuUri;
    @Column(name = "menu_ex")               private String menuEx;
    // @Column(name = "menu_bi_seq")           private Integer menuBiSeq;
    @ManyToOne @JoinColumn(name = "menu_bi_seq") BurgerInfoEntity burger;
    // @Column(name = "menu_side_seq")         private Integer menuSideSeq;
    @ManyToOne @JoinColumn(name = "menu_side_seq") SideInfoEntity side;
    // @Column(name = "menu_di_seq")           private Integer menuDiSeq;
    @ManyToOne @JoinColumn(name = "menu_di_seq") DrinkInfoEntity drink;
    // @Column(name = "menu_dog_seq")          private Integer menuDogSeq;
    @ManyToOne @JoinColumn(name = "menu_dog_seq") DogInfoEntity dog;
    @Column(name = "menu_size")             private Integer menuSize;
    @Column(name = "menu_select")     @ColumnDefault("false")      
    private Boolean menuSelect;
}
