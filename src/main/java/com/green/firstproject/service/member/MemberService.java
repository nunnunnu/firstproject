package com.green.firstproject.service.member;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.utils.AESAlgorithm;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.member.MemberMypageVO;
import com.green.firstproject.vo.member.UserUpdateVO;


import jakarta.servlet.http.HttpSession;
@Service
public class MemberService {
    @Autowired MemberInfoReposiroty mRepo;
    public Map<String, Object> addMember(MemberInfoEntity data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(mRepo.countByMiEmail(data.getMiEmail()) == 1){
            resultMap.put("status", false);
            resultMap.put("message", data.getMiEmail()+"은/는 이미 가입된 계정입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(data.getMiPwd().length() < 6){
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호는 6자리 이상 입력하여 주십시오.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else{
            try{
                String encPwd = AESAlgorithm.Encrypt(data.getMiPwd());
                data.setMiPwd(encPwd);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            mRepo.save(data);
            resultMap.put("status", true);
            resultMap.put("message", "회원 가입이 완료되었습니다.");
            resultMap.put("code", HttpStatus.CREATED);
        }
        return resultMap;
    }
    
    public Map<String, Object> findEmail(String name, String phone){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        for(MemberInfoEntity data : mRepo.findAll()){
            if (data.getMiName().equals(name) && data.getMiPhone().equals(phone)) {
            if(mRepo.countByMiNameAndMiPhone(data.getMiName(), data.getMiPhone()) == 1){
                resultMap.put("status", true);
                resultMap.put("message", "회원님의 이메일은"+data.getMiEmail()+" 입니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
            }
        }
            else{
                resultMap.put("status", false);
                resultMap.put("message", "등록된 회원 정보가 없습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        }
        return resultMap;
    }

    public Map<String, Object> findPwd(String name, String email){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        for(MemberInfoEntity data : mRepo.findAll()){
            if (data.getMiName().equals(name) && data.getMiEmail().equals(email)) {
            if(mRepo.countByMiNameAndMiEmail(data.getMiName(), data.getMiEmail()) == 1){
                try{
                resultMap.put("status", true);
                resultMap.put("message", "회원님의 비밀번호는"+ AESAlgorithm.Decrypt(data.getMiPwd())+" 입니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
            else{
                resultMap.put("status", false);
                resultMap.put("message", "이름 또는 이메일을 확인하여 주십시오.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }
        }
        return resultMap;
    }
    
    public Map<String, Object> loginMember (LoginUserVO data){
            Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            MemberInfoEntity user = null;
            try{
                
                user = mRepo.findByMiEmailAndMiPwd(data.getEmail(), AESAlgorithm.Encrypt(data.getPwd()));
            } catch(Exception e){e.printStackTrace();}
            if(user == null){
                resultMap.put("status", false);
                resultMap.put("message", "이메일 또는 비밀번호 오류입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }else if(user.getMiStatus()==2){
                resultMap.put("status", false);
                resultMap.put("message", "탈퇴한 회원입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
            }else{
                LoginUserVO loginUser = new LoginUserVO(user);
                resultMap.put("status", true);
                resultMap.put("message", "로그인 되었습니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
                resultMap.put("loginUser", loginUser);
            }
            return resultMap;
        }
        
        public Map<String, Object> updateMember(LoginUserVO data, String pwd, String changePwd, String phone, Integer gen) {
            Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            MemberInfoEntity member = new MemberInfoEntity();
            try {
                member = mRepo.findByMiEmailAndMiPwd(data.getEmail(), AESAlgorithm.Encrypt(pwd));
                if (member == null) {
                    resultMap.put("status", false);
                    resultMap.put("message", "이메일 또는 비밀번호 오류입니다.");
                    resultMap.put("code", HttpStatus.BAD_REQUEST);
                    return resultMap;
                }
                if(changePwd!=null){
                    member.setMiPwd(AESAlgorithm.Encrypt(changePwd));
                }
                if(phone!=null){
                    member.setMiPhone(phone);
                }
                if(gen != null){
                    member.setMiGen(gen);
                }
                mRepo.save(member);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultMap.put("status", true);
            resultMap.put("message", "정보가 변경되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        public Map<String, Object> deleteMember(LoginUserVO data){
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            MemberInfoEntity member = mRepo.findByMiEmailAndMiPwd(data.getEmail(), data.getPwd());
            if (member!=null) {
                member.setMiStatus(2);
                mRepo.save(member);
                map.put("status", true);
                map.put("message", "회원 탈퇴 되었습니다.");
                map.put("code", HttpStatus.ACCEPTED);
                return map;
            }
            map.put("status", false);
            map.put("message", "로그인 해주세요!");
            map.put("code", HttpStatus.BAD_REQUEST);
            return map;
        
    }
    public Map<String, Object> memberMypage (LoginUserVO data){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiEmailAndMiPwd(data.getEmail(), data.getPwd());
            if (member == null) {
                map.put("status", false);
                map.put("message", "이메일 또는 비밀번호 오류입니다.");
                map.put("code", HttpStatus.BAD_REQUEST);
                return map;
            }
            MemberMypageVO memberVo = new MemberMypageVO(member);
            map.put("list", memberVo);
            map.put("message", "회원정보 조회완료!");
            map.put("code", HttpStatus.ACCEPTED);
            return map;
            
    }
}    
