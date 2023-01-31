package com.green.firstproject.service.store;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.vo.store.StoreInfoVO;
import com.green.firstproject.vo.store.StoreOpenVO;
import com.green.firstproject.vo.store.StoreVO;

import io.micrometer.common.lang.Nullable;



@Service
public class StoreInfoService {
    @Autowired StoreInfoRepository siRepo;
    public Map<String, Object> getStoreInfo(Pageable pageable, @Nullable String keyword){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Page<StoreInfoEntity> list = siRepo.findAll(pageable);
        Page<StoreInfoVO> result = list.map(s->new StoreInfoVO(s));

        resultMap.put("message", "모든 매장을 조회했습니다.");
        resultMap.put("code", HttpStatus.ACCEPTED);
        resultMap.put("list", result);
        return resultMap;
    }
    public Map<String, Object> getStoreDetailInfo(Pageable pageable, @Nullable String keyword){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            List<StoreInfoEntity> list = siRepo.searchStoreName(keyword);
            List<StoreInfoVO> result = new ArrayList<>();
            for(StoreInfoEntity s : list){
                StoreInfoVO store = new StoreInfoVO(s);
                result.add(store);
            }

            if(list.size()==0){
                resultMap.put("message", "조회된 매장이 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            resultMap.put("message", "조회하였습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("list", result);
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
            if(storeinfo.getStoreStatus() != null){
                store.setSiStatus(storeinfo.getStoreStatus());
            }
            if(storeinfo.getStorePhone() != null){
                store.setSiPhone(storeinfo.getStorePhone());
            }
            if(storeinfo.getStoreOpenTime() != null){
                store.setSiOpenTime(storeinfo.getStoreOpenTime());
            }
            if(storeinfo.getStoreCloseTime() != null){
                store.setSiCloseTime(storeinfo.getStoreCloseTime());
            }
            if(storeinfo.getStoreMinOrderAmount() != null){
                store.setSiMinOrderAmount(storeinfo.getStoreMinOrderAmount());
            }
            siRepo.save(store);
            resultMap.put("status", true);
            resultMap.put("message", "가게 정보가 변경되었습니다.");
            resultMap.put("code", HttpStatus.OK);
        }
        System.out.println(store);
        return resultMap;

    }

    public Map<String, Object> findStore(String address){
        Map<String, Object> map = new LinkedHashMap<>();

        List<StoreInfoEntity> store = siRepo.findAll();
        StoreInfoEntity entity = new StoreInfoEntity();
        for(StoreInfoEntity s : store){
            if(address.contains(s.getSiDeliveryArea())){
                entity = s;
            }
        }
        if(entity.getSiSeq()==null){
            map.put("status", false);
            map.put("message", "배달 불가능한 지역입니다.");
            map.put("code", HttpStatus.NO_CONTENT);
        }else{
            StoreVO result =  new StoreVO(entity);
            map.put("store", result);
            map.put("status", true);
            map.put("message", "매장을 선택했습니다.");
            map.put("code", HttpStatus.OK);
        }

        return map;
    }

    public Map<String, Object> getStoreOpenStatus(Long seq){
        Map<String, Object> map = new LinkedHashMap<>();
        StoreInfoEntity entity = siRepo.findBySiSeq(seq);
        if(entity==null){
            map.put("status", false);
            map.put("message", "매장 번호 확인해주세요.");
            map.put("code",HttpStatus.BAD_REQUEST);
        }
        else{
        StoreOpenVO store = new StoreOpenVO(entity);
        map.put("store", store);
        map.put("status", true);
        map.put("message", "매장 오픈 여부 조회");
        map.put("code",HttpStatus.OK);
        }
        return map;
    }
}