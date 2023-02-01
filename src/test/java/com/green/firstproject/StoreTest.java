package com.green.firstproject;

import java.time.LocalTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.service.store.StoreInfoService;

@SpringBootTest
public class StoreTest {
    @Autowired StoreInfoRepository siRepo;
    @Autowired StoreInfoService siService;
    @Test
    @Transactional
    void 매장검색(){
        StoreInfoEntity store = new StoreInfoEntity(null, "매장이름", "매장주소", "상세주소", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1,"동성로","31.1111","32.0000");
        siRepo.save(store);
        StoreInfoEntity result = siRepo.findBySiName("매장이름");
        Assertions.assertThat(result.equals(store));
        
    }
    @Test
    @Transactional
    void 정보수정(){
        StoreInfoEntity store = new StoreInfoEntity(null, "매장이름", "매장주소", "상세주소", "053-000-000", LocalTime.now(), LocalTime.now(), 13000, 1,"동성로",null,null);
        siRepo.save(store);
        String originPhone = store.getSiPhone();
        store.setSiPhone("010-1101-1101");
        siRepo.save(store);
        StoreInfoEntity findStore = siRepo.findBySiSeq(store.getSiSeq());  
        Assertions.assertThat(originPhone).isNotEqualTo(findStore.getSiPhone());   
    }
    @Test
    @Transactional
    void 전체조회(){
        List<StoreInfoEntity> result = siRepo.findAll();
        // StoreInfoEntity store = siRepo.findAll().get(0);
    }

}
