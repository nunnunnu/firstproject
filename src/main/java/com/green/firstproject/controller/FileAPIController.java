package com.green.firstproject.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.service.category.MenuService;
import com.green.firstproject.service.file.FileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileAPIController {
    @Autowired MenuService mService;
    @Autowired FileService fService;
    @Value("${file.image.burger}") String burger_img_path;
    @Value("${file.image.side}") String side_img_path;
    @Value("${file.image.drink}") String drink_img_path;
    @Value("${file.image.dog}") String dog_img_path;
    @Value("${file.image.menu}") String menu_img_path;
    @Value("${file.image.event}") String event_img_path;
    @Value("${file.image.ingredients}") String ingredients_img_path;

@GetMapping("/images/{type}/{uri}") 
   public ResponseEntity<Resource> getImage ( @PathVariable String uri, 
            @PathVariable String type , HttpServletRequest request ) throws Exception { 
                return fService.getImage(uri, type, request);
    }
}
