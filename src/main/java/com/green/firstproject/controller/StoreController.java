package com.green.firstproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.service.store.StoreInfoService;
import com.green.firstproject.vo.store.StoreInfoVO;

import io.micrometer.common.lang.Nullable;

@RestController
public class StoreController {
    @Autowired StoreInfoRepository siRepo;
    @Autowired StoreInfoService siService;
    @GetMapping("/store")
    public ResponseEntity<Object> storeInfo(@PageableDefault(size = 8) Pageable pageable, String keyword){
    Map<String, Object> resultMap = siService.getStoreInfo(pageable, keyword);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getStoreSearch (
        @PageableDefault(size = 8) Pageable pageable, @RequestParam @Nullable String keyword
        ) {
            if(keyword==null) keyword="";
            return new ResponseEntity<>(siService.getStoreDetailInfo(pageable, keyword), HttpStatus.OK);
        }
        @PatchMapping("/update/{seq}")
        public ResponseEntity<Object> updateStoreInfo(@RequestBody StoreInfoVO data, @PathVariable Long seq
        ) {
            Map<String, Object> map = new LinkedHashMap<>();
            if(seq == null){
                map.put("status", false);
                map.put("message", "로그인 먼저 해주세요.");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
            map = siService.updateStoreInfo(data, seq);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
}