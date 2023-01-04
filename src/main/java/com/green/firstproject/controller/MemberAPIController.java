package com.green.firstproject.controller;

import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.service.member.MemberService;
import com.green.firstproject.vo.member.FindIdVO;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @Autowired MemberService mService;
    @PutMapping("/join")
    public ResponseEntity<Object> memberJoin(@RequestBody MemberInfoEntity data){
        Map<String, Object> resultMap = mService.addMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
      }

    @GetMapping("/email")
    public ResponseEntity<Object> findEmail(@RequestParam String name, @RequestParam String phone){
        Map<String, Object> resultMap = mService.findEmail(name, phone);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }

    @GetMapping("/pwd")
    public ResponseEntity<Object> findPwd(@RequestParam String name, @RequestParam String email){
        Map<String, Object> resultMap = mService.findPwd(name, email);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
      
}
