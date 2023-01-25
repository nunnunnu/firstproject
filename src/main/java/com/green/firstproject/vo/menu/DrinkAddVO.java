package com.green.firstproject.vo.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkAddVO {
    @Autowired DrinkInfoRepository dRepo;
    @Autowired CategoryRepository cateRepo;
    private String dinkeName;
    private Long category;
    private String drinkDetail;
    private String diFile;
    private String diUri;
    private MultipartFile drinkFile;
}
