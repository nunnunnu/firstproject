package com.green.firstproject.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.utils.AESAlgorithm;
import com.green.firstproject.vo.member.LoginUserVO;
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
        }
        else{
            LoginUserVO loginUser = new LoginUserVO(user);
            resultMap.put("status", true);
            resultMap.put("message", "로그인 되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("loginUser", loginUser);
        }
        return resultMap;
        }
}
