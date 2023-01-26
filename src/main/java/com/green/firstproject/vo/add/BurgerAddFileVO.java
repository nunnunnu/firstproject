package com.green.firstproject.vo.add;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//HTML Form을 이용해서 버거 메뉴 등록

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BurgerAddFileVO {
    @Autowired BurgerInfoRepository bRepo;
    @Autowired CategoryRepository cateRepo;
    private String name;
    private Long cate;
    private String detail;
    private MultipartFile file;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDt;  
}
