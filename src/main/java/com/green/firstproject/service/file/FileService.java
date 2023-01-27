package com.green.firstproject.service.file;

import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileService {
    @Autowired BurgerInfoRepository bRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired SideInfoRepository sideRepo;
    @Autowired DrinkInfoRepository  dRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired IngredientsInfoRepository iRepo;
    @Autowired EventInfoRepository eRepo;
    @Autowired MenuInfoRepository menuRepo;
    @Autowired DrinkOptionRepository drinkOptRepo;
    @Autowired SideOptionRepository sideOptRepo;

    @Value("${file.image.burger}") String burger_img_path;
    @Value("${file.image.side}") String side_img_path;
    @Value("${file.image.drink}") String drink_img_path;
    @Value("${file.image.dog}") String dog_img_path;
    @Value("${file.image.menu}") String menu_img_path;
    @Value("${file.image.event}") String event_img_path;
    @Value("${file.image.ingredients}") String ingredients_img_path;
    @Value("${file.image.drinkOpt}")String drinkOpt_img_path;
    @Value("${file.image.sideOpt}")String sideOpt_img_path;

    public ResponseEntity<Resource> getImage ( @PathVariable String uri, 
    @PathVariable String type , HttpServletRequest request ) throws Exception { 
    Path folderLocation = null;
    String filename = null;
    // 내보낼 파일의 이름을 만든다. 
    // 폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로를 만든다. 
    if(type.equals("burger")){
            folderLocation = Paths.get(burger_img_path);
            filename = bRepo.findByBiUri(uri).getBiFile();
        }
        else if(type.equals("side")){
            folderLocation = Paths.get(side_img_path);
            filename = sideRepo.findBySideUri(uri).getSideFile();
        }
        else if(type.equals("drink")){
            folderLocation = Paths.get(drink_img_path);
            System.out.println("aaa");
            filename = dRepo.findByDiUri(uri).getDiFile();
            System.out.println(filename);
        }
        else if(type.equals("dog")){
            folderLocation = Paths.get(dog_img_path);
            filename = dogRepo.findByDogUri(uri).getDogFile();
        }
        else if(type.equals("menu")){
            folderLocation = Paths.get(menu_img_path);
            filename = menuRepo.findByMenuUri(uri).getMenuFile();
        }
        else if(type.equals("event")){
            folderLocation = Paths.get(event_img_path);
            filename = eRepo.findByEiUri(uri).getEiFile();
        }
        else if(type.equals("ingredients")){
            folderLocation = Paths.get(ingredients_img_path);
            filename = iRepo.findByIiUri(uri).getIiFile();
        }
        else if (type.equals("drinkOpt")) {
            folderLocation = Paths.get(drinkOpt_img_path);
            filename = drinkOptRepo.findByDoUri(uri).getDoFile();
        }
        else if (type.equals("sideOpt")) {
            folderLocation = Paths.get(sideOpt_img_path);
            filename = sideOptRepo.findBySoUri(uri).getSoFile();
        }
        String[] split = filename.split("\\.");
        String ext = split[split.length - 1];
        String exportName = uri + "." + ext;
        
        Path targerFile = folderLocation.resolve(filename); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
        //다운로드 가능한 형태로 변환하기 위해 Resource객체 생성함
        Resource r = null;
        try {
            // 일반파일 -> Url로 첨부 가능한 형태로 변환 
            r = new UrlResource(targerFile.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 첨부된 파일의 타입을 저장하기위한 변수 생성 
        String contentType = null;
        try {
            // 첨부할 파일의 타입 정보 산출 
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            // 산출한 파일의 타입이 null 이라면 
            if (contentType == null) {
                // 일반 파일로 처리한다. 
                contentType = "application/octet-stream";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
            // 응답의 코드를 200 OK로 설정하고 
            // 산출한 타입을 응답에 맞는 형태로 변환 
            .contentType(MediaType.parseMediaType(contentType))
            // 내보낼 내용의 타입을 설정 (파일), 
            // attachment; filename*=\""+r.getFilename()+"\" 요청한 쪽에서 다운로드 한 
            // 파일의 이름을 결정 
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"")
            .body(r);
        // 변환된 파일을 ResponseEntity에 추가 }
    }
}
