package com.green.firstproject.vo.menu;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SideAddVO {
    @Autowired SideInfoRepository sideRepo;
    @Autowired CategoryRepository cateRepo;
    private String name;
    private Long cate;
    private String detail;
    private String file;
    private String uri; 
}
