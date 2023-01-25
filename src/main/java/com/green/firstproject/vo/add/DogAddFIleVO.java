package com.green.firstproject.vo.add;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogAddFIleVO {
    private String name;
    private Long category;
    private String detail;
    private MultipartFile dogFile;
}
