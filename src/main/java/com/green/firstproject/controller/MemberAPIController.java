package com.green.firstproject.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.service.member.MemberService;
import com.green.firstproject.vo.member.LoginUserVO;

import jakarta.servlet.http.HttpSession;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
// import com.green.firstproject.service.MemberService;


@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @Autowired MemberService mService;
    @Autowired MenuInfoRepository menuRepo;
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
      resultMap.put("message", "로그인 먼저 해주세요.");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
      return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    session.setAttribute("loginUser", null);
    resultMap.put("status", true);
    resultMap.put("message", "로그아웃 되었습니다.");
    resultMap.put("code", HttpStatus.ACCEPTED);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }
  
  @PatchMapping("/update")
  public ResponseEntity<Object> patchUserUpdate(LoginUserVO data, HttpSession session,
      @RequestParam String pwd, // 나중에 변경해야함
      @RequestParam String changePwd) {
    Map<String, Object> map = new LinkedHashMap<>();
    LoginUserVO loginUser = (LoginUserVO) session.getAttribute("loginUser");
    if (loginUser == null) {
      map.put("status", false);
      map.put("message", "로그인 먼저 해주세요.");
      map.put("code", HttpStatus.ACCEPTED);
      return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    }
    map = mService.updateMember(loginUser, pwd, changePwd);

    return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
  }
}
