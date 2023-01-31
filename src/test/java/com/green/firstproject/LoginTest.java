package com.green.firstproject;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.service.member.MemberService;
import com.green.firstproject.vo.member.LoginUserVO;

@SpringBootTest
public class LoginTest {
    @Autowired MemberInfoReposiroty mRepo;
    @Autowired MemberService memberService;
    @Test
    @Transactional
    void 로그인(){
        String email = "user001@email.com";
        String pwd = "123456";
        MemberInfoEntity member = new MemberInfoEntity(null, email,pwd, "이름", "010-0000-0000", 1, LocalDate.now(), null, null);
        mRepo.save(member);
        MemberInfoEntity findMember = mRepo.findByMiEmailAndMiPwd(email, pwd);
        
        LoginUserVO loginUser = new LoginUserVO(findMember);
        
        Assertions.assertThat(loginUser.getSeq()).isNotEqualTo(null);
    }
}