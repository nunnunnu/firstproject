package com.green.firstproject.vo.add;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//HTML Form을 이용해서 사이드 메뉴 등록

@Data
public class SideAddFileVO {
    private String sideTitle;
    private String sideDetail;
    private Long category;
    private MultipartFile sideFile;

}
