package com.green.firstproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
import com.green.firstproject.service.menu.MenuInfoService;

import io.micrometer.common.lang.Nullable;

@RestController
public class MenuInfoCotroller {
    @Autowired MenuInfoRepository menuRepo;
    @Autowired MenuInfoService menuService;
    @GetMapping("/burger")
    public ResponseEntity<Object> getBuregerInfo(
        @RequestParam Long seq) {
            Map<String, Object> map = menuService.getBuregerInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    @GetMapping("/dog")
    public ResponseEntity<Object> getDogInfo(
        @RequestParam Long seq){
            Map<String, Object> map = menuService.getDogInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
    @GetMapping("/drink")
    public ResponseEntity<Object> getDrinkInfo(
        @RequestParam Long seq){
            Map<String, Object> map = menuService.getDrinkInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
    @GetMapping("/side")
    public ResponseEntity<Object> getSideInfo(
        @RequestParam Long seq){
            Map<String, Object> map = menuService.getSideInfo(seq);
            return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
        }
}