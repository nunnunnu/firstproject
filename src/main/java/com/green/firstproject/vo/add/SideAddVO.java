package com.green.firstproject.vo.add;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SideAddVO {
    private String sideTitle;
    private String sideDetail;
    private Long category;
    private MultipartFile sideFile;

}
