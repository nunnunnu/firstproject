package com.green.firstproject;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.member.MemberInfoReposiroty;

@SpringBootTest
public class LoginTest {
    @Autowired MemberInfoReposiroty mRepo;
    @Test
    void testLogin(){
        String email = "member1@email.com";
        String pwd = "1234";
        MemberInfoEntity loginUser = mRepo.findByMiEmailAndMiPwd(email, pwd);
        assertNotEquals(loginUser, null);
    }
}