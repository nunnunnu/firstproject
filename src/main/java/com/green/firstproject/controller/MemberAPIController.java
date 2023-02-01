package com.green.firstproject.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.menu.sellermenu.MenuInfoRepository;
// import com.green.firstproject.service.MemberService;
import com.green.firstproject.service.member.MemberService;
import com.green.firstproject.vo.member.DeliveryVO;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.member.MyDeliveryVO;
import com.green.firstproject.vo.member.UserUpdateVO;



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
    @GetMapping("/login")
    public ResponseEntity<Object> memberLogin(@RequestBody LoginUserVO data){
      Map<String, Object> resultMap = mService.loginMember(data);
      return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
  //   @GetMapping("/logout")
  //   public ResponseEntity<Object> memberLogout(HttpSession session){
  //     Map<String, Object> resultMap = new LinkedHashMap<>();
  //   if(session.getAttribute("loginUser") == null){
  //     resultMap.put("status", false);
  //     resultMap.put("message", "로그인 먼저 해주세요.");
  //     resultMap.put("code", HttpStatus.BAD_REQUEST);
  //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  //   }
  //   session.setAttribute("loginUser", null);
  //   resultMap.put("status", true);
  //   resultMap.put("message", "로그아웃 되었습니다.");
  //   resultMap.put("code", HttpStatus.ACCEPTED);
  //   return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  // }
  @PostMapping("/update/{seq}")//patch-> post
  public ResponseEntity<Object> userupdate(@PathVariable Long seq, @RequestBody UserUpdateVO userUpdate) {
    Map<String, Object> map = new LinkedHashMap<>();
      map = mService.updateMember(seq, userUpdate.getPwd(), userUpdate.getChangePwd(),
      userUpdate.getPhone(), userUpdate.getGen());
      return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
  }
  @DeleteMapping("/delete/{seq}")//회원정보 삭제
  public ResponseEntity<Object> deleteMember(@PathVariable Long seq){
    Map<String, Object> map = new LinkedHashMap<>();
    map = mService.deleteMember(seq);
    return new ResponseEntity<Object>(map, (HttpStatus) map.get("code"));
  }
  @GetMapping("/mypage/{seq}")//회원 정보 조회
  public ResponseEntity<Object> memberMypage(@PathVariable Long seq){
    Map<String, Object> map = new LinkedHashMap<>();
    map = mService.memberMypage(seq);
    return new ResponseEntity<Object>(map, (HttpStatus) map.get("code"));
  }
  @GetMapping("/address/lately/{seq}")
  public ResponseEntity<Object> myLatelyDeliveryAddress(@PathVariable Long seq){
    Map<String, Object> map = new LinkedHashMap<>();

    map = mService.showMyLatelyDelivery(seq);
    
    return new ResponseEntity<Object>(map, (HttpStatus) map.get("code"));
  }
  @GetMapping("/address/my/{seq}")
  public ResponseEntity<Object> myDeliveryAddress(@PathVariable Long seq){
    Map<String, Object> map = new LinkedHashMap<>();
    map = mService.showMyDeliveryAddress(seq);
    
    return new ResponseEntity<Object>(map, (HttpStatus) map.get("code"));
  }

  @PutMapping("/add/my")
  public ResponseEntity<Object> addMyDeliveryAddress(@RequestBody MyDeliveryVO data){
    Map<String, Object> resultMap = mService.addMyDeliveryAddress(data);
    return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
  }

  @DeleteMapping("/delete/my")
  public ResponseEntity<Object> deleteMyDeliveryAddress(@RequestParam Long member, @RequestParam Long mySeq){
    System.out.println(member);
    System.out.println(mySeq);
    Map<String, Object> resultMap = mService.deleteMyDeliveryAddress(member, mySeq);
    return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
  }

  @PatchMapping("/update/my/{seq}")
  public ResponseEntity<Object> updateMyDeliveryName(@PathVariable Long seq, @RequestBody MyDeliveryVO data){
    Map<String, Object> resultMap = mService.updateMyDeliveryName(data, seq);
    return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
  }

//   @PatchMapping("/update/my/basic/{seq}")
//   public ResponseEntity<Object> updateMyDeliveryBasic(HttpSession session, @PathVariable Long seq, @RequestBody MyDeliveryVO data){
//     LoginUserVO loginUser = (LoginUserVO) session.getAttribute("loginUser");
//     Map<String, Object> resultMap = mService.updateMyDeliveryBasic(loginUser, seq, data.getBasic());
//     return new ResponseEntity<Object>(resultMap, (HttpStatus) resultMap.get("code"));
//   }
}
