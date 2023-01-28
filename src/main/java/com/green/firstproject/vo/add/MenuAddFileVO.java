package com.green.firstproject.vo.add;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuAddFileVO {
    private String menuName;
    private Integer menuPrice;
    private String menuEx;
    private MultipartFile menufile;
    private Boolean menuselect;
    private Integer menuSize;
    private Long menuBiSeq;
    private Long menuEiSeq;
    private Long menuSideSeq;
    private Long menuDiSeq;
    private Long menuDi2Seq;
    private Long menuDogSeq;
}
