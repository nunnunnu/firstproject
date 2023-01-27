package com.green.firstproject.vo.add;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SideOptAddFileVO {
    private String soName;
    private Integer soPrice;
    private Integer soSize;
    private MultipartFile sideOptfile;
}
