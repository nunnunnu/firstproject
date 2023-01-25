package com.green.firstproject.service.menu;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.option.DrinkOptionEntity;
import com.green.firstproject.entity.menu.option.SideOptionEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;
import com.green.firstproject.repository.menu.CategoryRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.option.DrinkOptionRepository;
import com.green.firstproject.repository.menu.option.SideOptionRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.vo.add.BurgerAddFileVO;
import com.green.firstproject.vo.add.DogAddFIleVO;
import com.green.firstproject.vo.add.DrinkAddFileVO;
import com.green.firstproject.vo.add.SideAddVO;
import com.green.firstproject.vo.menu.option.DrinkOptionVO;
import com.green.firstproject.vo.menu.option.SideOptionVO;

@Service
public class MenuInfoService {
    @Autowired MenuInfoRepository menuRepo;
    @Autowired BurgerInfoRepository burgerRepo;
    @Autowired DogInfoRepository dogRepo;
    @Autowired DrinkInfoRepository drinkRepo;
    @Autowired SideInfoRepository sideRepo;
    @Autowired CategoryRepository cateRepo;
    @Autowired SideOptionRepository sideoptRepo;
    @Autowired DrinkOptionRepository drnikoptRepo;
    @Autowired EventInfoRepository eventRepo;

    public Map<String, Object> getBuregerInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        BurgerInfoEntity b = burgerRepo.findByBiSeq(seq);
        if(b==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 버거가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findByBurger(b);
        resultMap.put("list", list);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }
    public Map<String, Object> getDogInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DogInfoEntity dog = dogRepo.findByDogSeq(seq);
        if(dog==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 독퍼가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findBydog(dog);
        resultMap.put("list", list);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }

    public Map<String, Object> getDrinkInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        DrinkInfoEntity drink = drinkRepo.findByDiSeq(seq);
        if(drink==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 음료가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findByDrink(drink);
        if(list.size() == 0){
            resultMap.put("message", "해당하는 메뉴가 없습니다.");
        }
        List<MenuInfoEntity> list3 = menuRepo.findByDrink(drink);
        for(MenuInfoEntity m : list){
            if(m.getBurger()==null && m.getDog()==null && m.getSide()==null && m.getDrink()!=null){
                list3.add(m);
            }
        }
        resultMap.put("list", list3);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }
    public Map<String, Object> getSideInfo(Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        SideInfoEntity side = sideRepo.findBySideSeq(seq);
        if(side==null){
            resultMap.put("status", false);
            resultMap.put("message", "해당되는 사이드 메뉴가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<MenuInfoEntity> list = menuRepo.findBySide(side);
        if(list.size() == 0){
            resultMap.put("message", "해당하는 메뉴가 없습니다.");
        }
        List<MenuInfoEntity> list2 = new ArrayList<>();
        for(MenuInfoEntity m : list){
            if(m.getBurger()==null && m.getDrink()==null && m.getDog()==null && m.getSide()!=null){
                list2.add(m);
            }
        }
        resultMap.put("list", list2);
        resultMap.put("message", "조회하였습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        return resultMap;
    }

    
    public Map<String, Object> getSideOptionInfo(Long seq) { 
        Map<String, Object> resultMap = new LinkedHashMap<>();
        MenuInfoEntity menu = menuRepo.findByMenuSeq(seq);
        if(menu == null){
            resultMap.put("status", false);
            resultMap.put("message", "메뉴 번호가 잘못되었습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<SideOptionEntity> list = new ArrayList<>();
        if(menu.getMenuSize() == 1){
            list = sideoptRepo.findAll();
        }else if(menu.getMenuSize() == 2){
            list = sideoptRepo.findBySoSize(menu.getMenuSize());
        }
            List<SideOptionVO> result = new ArrayList<>();
            for(SideOptionEntity s : list){
                SideOptionVO side = new SideOptionVO(s);
                result.add(side);
            }
            
            resultMap.put("message", "사이드 옵션을 조회하였습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("list", result);
            return resultMap;
        }

    public Map<String, Object> getDrinkOptionInfo(Long seq) { 
        Map<String, Object> resultMap = new LinkedHashMap<>();
        MenuInfoEntity menu = menuRepo.findByMenuSeq(seq);
        if(menu == null){
            resultMap.put("status", false);
            resultMap.put("message", "메뉴 번호가 잘못되었습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
        List<DrinkOptionEntity> list = new ArrayList<>();
        if(menu.getMenuSize() == 1){
            list = drnikoptRepo.findAll();
        }else if(menu.getMenuSize() == 2){
            list = drnikoptRepo.findByDoSize(menu.getMenuSize());
        }
        List<DrinkOptionVO> result = new ArrayList<>();
        for(DrinkOptionEntity d : list){
            DrinkOptionVO drink = new DrinkOptionVO(d);
            result.add(drink);
        }
        
        resultMap.put("message", "음료 옵션을 조회하였습니다");
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("list", result);
        return resultMap;
    }

    // public Map<String, Object> getNewMenu(Long seq){
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    //     LocalDate now = LocalDate.now();
    //     // SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    //     // f.format(now);
    //     List<MenuListVO> result = new ArrayList<>();
    //     for(BurgerInfoEntity b : burgerRepo.findAll()){
    //         MenuListVO data = new MenuListVO(b);
    //         // if((Period.between(now, data.getRegDt()).getDays() <= 30)&&(Period.between(now, data.getRegDt()).getYears() == 0)){
    //         //     data.setStatus(true);
    //         // }
    //         result.add(data);
    //     }
    //     resultMap.put("status", true);
    //     resultMap.put("message", "신제품 목록 입니다.");
    //     resultMap.put("code", HttpStatus.ACCEPTED);
    //     resultMap.put("list", result);
    //     return resultMap;
    // }
    
    @Value("${file.image.side}") String side_img_path; //springframework.beans임

    public void saveFile(SideAddVO data){

        MultipartFile file = data.getSideFile();
        
        Path folderLocation = null; //todo_img_path 문자열로부터 실제 폴더 경로를 가져옴.
        folderLocation = Paths.get(side_img_path);

        String originFileName = file.getOriginalFilename();
        String[] split = originFileName.split(("\\.")); //.을 기준으로 나눔
        String ext = split[split.length - 1]; //확장자
        String fileName = "";
        for (int i = 0; i < split.length - 1; i++) {
            fileName += split[i]; //원래 split[i]+"." 이렇게 해줘야함
        }
        String saveFileName = "side_"; //보통 원본 이름을 저장하는것이아니라 시간대를 입력함
        Calendar c = Calendar.getInstance();
        saveFileName += c.getTimeInMillis() + "." + ext; // todo_161310135.png 이런식으로 저장됨

        Path targetFile = folderLocation.resolve(saveFileName); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
        try {
            //Files는 파일 처리에 대한 유틸리티 클래스
            //copy - 복사, file.getInputStream() - 파일을 열어서 파일의 내용을 읽는 준비
            //targetFile 경로로, standardCopyOption.REPLACE_EXISTING - 같은 파일이 있다면 덮어쓰기.
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
            SideInfoEntity entity = 
                SideInfoEntity.builder().sideName(data.getSideTitle()).cate(cateRepo.findByCateSeq(data.getCategory()))
                                        .sideDetail(data.getSideDetail())
                                        .sideFile(saveFileName).sideUri(fileName).build();

        sideRepo.save(entity);
    }

    @Value("${file.image.burger}") String burger_img_path; //springframework.beans임
    public void saveFile(BurgerAddFileVO data){
        MultipartFile file = data.getFile();
        Path folderLocation = null; //todo_img_path 문자열로부터 실제 폴더 경로를 가져옴.
        folderLocation = Paths.get(burger_img_path);

        String originFileName = file.getOriginalFilename();
        String[] split = originFileName.split(("\\.")); //.을 기준으로 나눔
        String ext = split[split.length - 1]; //확장자
        String fileName = "";
        for (int i = 0; i < split.length - 1; i++) {
            fileName += split[i]; //원래 split[i]+"." 이렇게 해줘야함
        }

        String saveFileName = "burger_"; //보통 원본 이름을 저장하는것이아니라 시간대를 입력함
        Calendar c = Calendar.getInstance();
        saveFileName += c.getTimeInMillis() + "." + ext; // todo_161310135.png 이런식으로 저장됨

        Path targetFile = folderLocation.resolve(saveFileName); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
        try {
            //Files는 파일 처리에 대한 유틸리티 클래스
            //copy - 복사, file.getInputStream() - 파일을 열어서 파일의 내용을 읽는 준비
            //targetFile 경로로, standardCopyOption.REPLACE_EXISTING - 같은 파일이 있다면 덮어쓰기.
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BurgerInfoEntity entity = 
            BurgerInfoEntity.builder().biName(data.getName()).cate(cateRepo.findByCateSeq(data.getCate()))
                                    .biDetail(data.getDetail()).biRegDt(data.getRegDt())
                                    .biFile(saveFileName).biUri(fileName).build();
    
    burgerRepo.save(entity);
    }
    @Value("${file.image.drink}") String drink_img_path;
    public void saveDrinkFile(DrinkAddFileVO data){
        MultipartFile file = data.getDiFile();
        Path folderLocation = null; 
        folderLocation = Paths.get(drink_img_path);

        String originFileName = file.getOriginalFilename();
        String[] split = originFileName.split(("\\.")); //.을 기준으로 나눔
        String ext = split[split.length - 1]; //확장자
        String fileName = "";
        for (int i = 0; i < split.length - 1; i++) {
            fileName += split[i]; //원래 split[i]+"." 이렇게 해줘야함
        }
        String saveFileName = "drink_"; //보통 원본 이름을 저장하는것이아니라 시간대를 입력함
        Calendar c = Calendar.getInstance();
        saveFileName += c.getTimeInMillis() + "." + ext; // todo_161310135.png 이런식으로 저장됨

        Path targetFile = folderLocation.resolve(saveFileName); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
        try {
            //Files는 파일 처리에 대한 유틸리티 클래스
            //copy - 복사, file.getInputStream() - 파일을 열어서 파일의 내용을 읽는 준비
            //targetFile 경로로, standardCopyOption.REPLACE_EXISTING - 같은 파일이 있다면 덮어쓰기.
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DrinkInfoEntity entity = 
            DrinkInfoEntity.builder().diName(data.getName()).cate(cateRepo.findByCateSeq(data.getCategory()))
                                        .diDetail(data.getDetail())
                                        .diFile(saveFileName).diUri(fileName).build();
        drinkRepo.save(entity);
    }
    
    @Value("${file.image.dog}")String dog_img_path;
    
    public void saveDogFile(DogAddFIleVO data) {
        MultipartFile file = data.getDogFile();
        Path folderLocation = null;
        folderLocation = Paths.get(dog_img_path);

        String originFileName = file.getOriginalFilename();
        String[] split = originFileName.split(("\\.")); // .을 기준으로 나눔
        String ext = split[split.length - 1]; // 확장자
        String fileName = "";
        for (int i = 0; i < split.length - 1; i++) {
            fileName += split[i]; // 원래 split[i]+"." 이렇게 해줘야함
        }
        String saveFileName = "dog_"; // 보통 원본 이름을 저장하는것이아니라 시간대를 입력함
        Calendar c = Calendar.getInstance();
        saveFileName += c.getTimeInMillis() + "." + ext; // todo_161310135.png 이런식으로 저장됨

        Path targetFile = folderLocation.resolve(saveFileName); // 폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
        try {
            // Files는 파일 처리에 대한 유틸리티 클래스
            // copy - 복사, file.getInputStream() - 파일을 열어서 파일의 내용을 읽는 준비
            // targetFile 경로로, standardCopyOption.REPLACE_EXISTING - 같은 파일이 있다면 덮어쓰기.
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DogInfoEntity entity = DogInfoEntity.builder().dogName(data.getName())
                .cate(cateRepo.findByCateSeq(data.getCategory()))
                .dogDetail(data.getDetail())
                .dogFile(saveFileName).dogUri(fileName).build();
        dogRepo.save(entity);
    }
}

