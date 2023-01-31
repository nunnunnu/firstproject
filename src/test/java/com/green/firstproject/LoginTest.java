package com.green.firstproject;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.repository.master.GradeInfoRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;

import jakarta.transaction.Transactional;

@SpringBootTest
public class LoginTest {
    @Autowired MemberInfoReposiroty mRepo;
    @Autowired GradeInfoRepository gRepo;
    @Test
    void testLogin(){
        String email = "member1@email.com";
        String pwd = "1234";
        MemberInfoEntity loginUser = mRepo.findByMiEmailAndMiPwd(email, pwd);
        System.out.println(loginUser);
       // assertNotEquals(loginUser, null);
    }
    @Transactional
    @Test
    void 회원정보변경(){
        MemberInfoEntity member = new MemberInfoEntity(null, "test@email.com", "123456", "회원", "010-0000-0000", 1, null, 1, gRepo.findAll().get(0));
        mRepo.save(member);
        String pwd = member.getMiPwd();
        Integer gen = member.getMiGen();
        String phone = member.getMiPhone();
        member.setMiPwd("123457");
        member.setMiGen(0);
        member.setMiPhone("010-0000-2222");
        mRepo.save(member);
        MemberInfoEntity chgMember = mRepo.findByMiSeq(member.getMiSeq());
        // MemberInfoEntity chggen = mRepo.findByMiSeq(member.getMiGen());

        Assertions.assertThat(pwd).isNotEqualTo(chgMember.getMiPwd());
        Assertions.assertThat(gen).isNotEqualTo(chgMember.getMiGen());
        Assertions.assertThat(phone).isNotEqualTo(chgMember.getMiPhone());
    }
}