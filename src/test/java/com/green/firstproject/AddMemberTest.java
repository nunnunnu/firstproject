package com.green.firstproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.utils.AESAlgorithm;
@SpringBootTest
public class AddMemberTest {
    @Autowired MemberInfoReposiroty mRepo;
    @Test   
    void addMemeber(){
        MemberInfoEntity data=new MemberInfoEntity();
        data.setMiEmail("user001@email.com");
        data.setMiName("사용자");
        data.setMiPhone("010-0000-1111");
        data.setMiGen(0);          
        // data.setMiGrade(1);
        // data.setMiStatus(1);
        try{
            String encPwd = AESAlgorithm.Encrypt("123456");
            data.setMiPwd(encPwd);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        mRepo.save(data);
            // resultMap.put("status", true);
            // resultMap.put("message", "회원 가입이 완료되었습니다.");
            // resultMap.put("code", HttpStatus.CREATED);
    }
            
}
