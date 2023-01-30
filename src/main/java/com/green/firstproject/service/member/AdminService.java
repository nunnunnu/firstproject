package com.green.firstproject.service.member;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.firstproject.entity.member.AdminInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.member.AdminInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.vo.member.AdminInfoVO;
import com.green.firstproject.vo.member.AdminLoginVO;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {
    @Autowired AdminInfoRepository adRepo;
    public Map<String, Object> loginAdmin(AdminLoginVO login){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        AdminInfoEntity entity = adRepo.findByAdminEmailAndAdminPwd(login.getAdminEmail(), login.getAdminPwd());
        if(entity == null){
            map.put("status", false);
            map.put("message", "아이디 혹은 비밀번호 오류입니다.");
        }
        else if(entity.getAdminGrade().getGiSeq() != 99){
            map.put("status", false);
            map.put("message", "관리자 전용 페이지 입니다.");
        }
        else{
            map.put("status", true);
            map.put("message", "로그인 되었습니다.");
            map.put("login", new AdminInfoVO(entity));
        }
        return map;
    }

    @GetMapping("/logout")
    public String getAdminLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    public AdminInfoEntity getAdminInfo(Long adminNo){
        return adRepo.findById(adminNo).get();
    }
}
