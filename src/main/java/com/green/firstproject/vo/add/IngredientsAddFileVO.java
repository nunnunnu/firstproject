package com.green.firstproject.vo.add;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsAddFileVO {
    private String iiName;
    private Integer iiPrice;
    private MultipartFile ingredientsFile;
    private Long eventSeq;
}
