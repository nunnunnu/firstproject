package com.green.firstproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.firstproject.service.member.AdminService;
import com.green.firstproject.service.member.MemberService;
import com.green.firstproject.vo.member.AdminInfoVO;
import com.green.firstproject.vo.member.AdminLoginVO;
import com.green.firstproject.vo.member.LoginUserVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired AdminService adminService;
    @PostMapping("/login")
    public String postAdminLogin(AdminLoginVO login, HttpSession session, Model model){
        Map<String, Object> resurltMap = adminService.loginAdmin(login);
        if((boolean)resurltMap.get("status")){
            session.setAttribute("loginUser", resurltMap.get("login"));
            return "redirect:/main";
        }
        else{
            model.addAttribute("message", resurltMap.get("message"));
            return "/index";
        }
    }

    @GetMapping("/logout")
    public String getAdminLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/detail")
    public String getAdminDetail(@RequestParam Long adminNo, Model model, HttpSession session){
        AdminInfoVO admin = (AdminInfoVO)session.getAttribute("loginUser");
        if(admin == null){  // 로그인 상태가 아니라면
            return "redirect:/"; // 로그인 페이지로
        }
        else if(admin.getAdminGrade() != 99){ // 로그인 상태지만 마스터가 아니라면
            return "redirect:/main"; // 메인페이지로
        }
        model.addAttribute("admin_detail", adminService.getAdminInfo(adminNo));
        return "/admin/detail";
    }
}
