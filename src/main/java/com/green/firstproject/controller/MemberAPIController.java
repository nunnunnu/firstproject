package com.green.firstproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.service.MemberService;
import com.green.firstproject.vo.member.LoginUserVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @Autowired MemberService mService;
    @PutMapping("/join")
    public ResponseEntity<Object> memberJoin(@RequestBody MemberInfoEntity data){
        Map<String, Object> resultMap = mService.addMember(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
      }
    // @GetMapping("/login")
    @PostMapping("/login")
    public ResponseEntity<Object> memberLogin(@RequestBody LoginUserVO data, HttpSession session){
      Map<String, Object> resultMap = mService.loginMember(data);
      session.setAttribute("loginUser", resultMap.get("loginUser"));
      return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    @GetMapping("/logout")
    public ResponseEntity<Object> memberLogout(HttpSession session){
      Map<String, Object> resultMap = new LinkedHashMap<>();
    if(session.getAttribute("loginUser") == null){
      resultMap.put("status", false);
      resultMap.put("message", "로그인을 먼저 해주세요.");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
      return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    session.setAttribute("loginUser", null);
    resultMap.put("status", true);
    resultMap.put("message", "로그아웃 되었습니다.");
    resultMap.put("code", HttpStatus.ACCEPTED);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }
}