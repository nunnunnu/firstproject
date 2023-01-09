package com.green.firstproject.service.store;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.vo.store.StoreInfoVO;

import io.micrometer.common.lang.Nullable;



@Service
public class StoreInfoService {
    @Autowired StoreInfoRepository siRepo;
    public Map<String, Object> getStoreInfo(Pageable pageable, @Nullable String keyword){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("message", "모든 매장을 조회했습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("list", siRepo.findAll(pageable));
        return resultMap;
    }
    public Map<String, Object> getStoreDetailInfo(Pageable pageable, @Nullable String keyword){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            // StoreInfoEntity list = siRepo.findBySiName();
            // resultMap.put("status", true);
            resultMap.put("message", "조회하였습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("list", siRepo.searchStoreName(keyword));
            return resultMap;
        }
    public Map<String, Object> updateStoreInfo(StoreInfoVO storeinfo, Long seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        StoreInfoEntity store = siRepo.findBySiSeq(seq);
        if(store == null){
            resultMap.put("status", false);
            resultMap.put("message", "잘못된 가게 번호입니다.");
            resultMap.put("code", HttpStatus.FORBIDDEN);
        }
        else{
            System.out.println();
            if(storeinfo.getStatus() != null){
                store.setSiStatus(storeinfo.getStatus());
            }
            if(storeinfo.getPhone() != null){
                store.setSiPhone(storeinfo.getPhone());
            }
            if(storeinfo.getOpenTime() != null){
                store.setSiOpenTime(storeinfo.getOpenTime());
            }
            if(storeinfo.getClosTime() != null){
                store.setSiCloseTime(storeinfo.getClosTime());
            }
            if(storeinfo.getMinorderprice() != null){
                store.setSiMinOrderAmount(storeinfo.getMinorderprice());
            }
            siRepo.save(store);
            resultMap.put("status", true);
            resultMap.put("message", "가게 정보가 변경되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        }
        return resultMap;

    }
}