package com.green.firstproject.vo.add;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
