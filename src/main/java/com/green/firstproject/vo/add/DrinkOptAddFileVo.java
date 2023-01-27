package com.green.firstproject.vo.add;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkOptAddFileVo {
    private String doName;
    private Integer doPrice;
    private Integer doSize;
    private MultipartFile drinkOptfile;
}
