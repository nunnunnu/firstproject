package com.green.firstproject.vo.add;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventAddFileVO {
    @Autowired EventInfoRepository evetnRepo;
    @Autowired CategoryRepository cateRepo;
    private String name;
    private Long cate;
    private String detail;
    private MultipartFile eventfile;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDt;
}
