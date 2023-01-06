package com.green.firstproject.service.store;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;

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
}