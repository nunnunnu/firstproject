package com.green.firstproject;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.member.MemberInfoReposiroty;

@SpringBootTest
public class FindMemberIdTest {
    @Autowired MemberInfoReposiroty mRepo;
    
    @Test
    void getId(){
        MemberInfoEntity data = new MemberInfoEntity();
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // data.setMiName("사용자1");
        // data.setMiPhone("010-1234-5678");
        if(mRepo.countByMiNameAndMiPhone("사용자1", "010-1234-5678") == 1){
            System.out.println("회원님의 이메일은 "+data.getMiEmail()+" 입니다.");
        }
    }
}
