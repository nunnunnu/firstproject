package com.green.firstproject.service.member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.member.LatelyDeliveryEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.member.MyDeliveryEntity;
import com.green.firstproject.repository.member.LatelyDeliveryRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.repository.member.MyDeliveryRepository;
import com.green.firstproject.utils.AESAlgorithm;
import com.green.firstproject.vo.member.DeliveryVO;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.member.MemberMypageVO;
@Service
public class MemberService {
    @Autowired MemberInfoReposiroty mRepo;
    @Autowired LatelyDeliveryRepository ldRepo;
    @Autowired MyDeliveryRepository mdRepo;

    public Map<String, Object> addMember(MemberInfoEntity data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String phonePattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
        String passwordPattern = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$";
        if(mRepo.countByMiEmail(data.getMiEmail()) == 1){
            resultMap.put("status", false);
            resultMap.put("message", data.getMiEmail()+"은/는 이미 가입된 계정입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else if(!Pattern.matches(passwordPattern, data.getMiPwd())){ //공백없이 특수문자 가능 6자리 이상
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호는 공백없이 6자리 이상 가능합니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }else if(!Pattern.matches(emailPattern, data.getMiEmail())){
            resultMap.put("status", false);
            resultMap.put("message", "올바른 이메일 형식이 아닙니다. 이메일을 다시 확인해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }else if(!Pattern.matches(phonePattern, data.getMiPhone())){
            resultMap.put("status", false);
            resultMap.put("message", "올바른 전화번호 형식이 아닙니다. 번호를 다시 확인해주세요.");
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
        for(MemberInfoEntity data : mRepo.findAll()){ //쿼리문으로 변경
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
            String phonePattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
            String passwordPattern = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$";
            if(!Pattern.matches(passwordPattern, changePwd)){ //공백없이 특수문자 가능 6자리 이상
                resultMap.put("status", false);
                resultMap.put("message", "비밀번호는 공백없이 6자리 이상 가능합니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }else if(!Pattern.matches(phonePattern, phone)){
                resultMap.put("status", false);
                resultMap.put("message", "올바른 전화번호 형식이 아닙니다. 번호를 다시 확인해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
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
        public Map<String, Object> deleteMember(LoginUserVO data, Long seq){
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            MemberInfoEntity member = mRepo.findByMiEmailAndMiPwd(data.getEmail(), data.getPwd());
            if (member == null) {
                map.put("status", false);
                map.put("message", "로그인 해주세요!");
                map.put("code", HttpStatus.BAD_REQUEST);
                return map;
            }
            member.setMiStatus(2);
            mRepo.save(member);
            map.put("status", true);
            map.put("message", "회원 탈퇴 되었습니다.");
            map.put("code", HttpStatus.ACCEPTED);
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
        
    public Map<String, Object> showMyLatelyDelivery(LoginUserVO data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiEmail(data.getEmail());
        List<LatelyDeliveryEntity> list = ldRepo.findMember(member);

        if(list.size()==0){
            map.put("status", false);
            map.put("message", "최근 주문 내역이 없습니다.");
            map.put("code", HttpStatus.ACCEPTED);    
            return map;
        }
        List<DeliveryVO> result = new ArrayList<>();
        for(LatelyDeliveryEntity l : list){
            DeliveryVO late = new DeliveryVO(l);
            result.add(late);
        }
        map.put("status", true);
        map.put("message", "최근 주문내역을 조회했습니다.");
        map.put("code", HttpStatus.ACCEPTED);    
        map.put("list", result);
        return map;
    }

    public Map<String, Object> showMyDeliveryAddress(LoginUserVO data){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiEmail(data.getEmail());
        List<MyDeliveryEntity> list = mdRepo.findMember(member);

        if(list.size()==0){
            map.put("status", false);
            map.put("message", data.getEmail()+"님 \n"+"평소에 자주 배달받는 주소를 등록해 보세요.");
            map.put("code", HttpStatus.ACCEPTED);
        }
        List<DeliveryVO> result = new ArrayList<>();
        for(MyDeliveryEntity m : list){
            DeliveryVO my = new DeliveryVO(m);
            result.add(my);
        }
        map.put("status", true);
        map.put("message", "MY배달지");
        map.put("code", HttpStatus.ACCEPTED);
        map.put("list", result);
        return map;
    }

    public Map<String, Object> addMyDeliveryAddress(LoginUserVO data, String address, String detailAddress){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MyDeliveryEntity my = new MyDeliveryEntity();
        my.setMdAddress(address);
        my.setMdDetailAddress(detailAddress);
        mdRepo.save(my);
        map.put("status", true);
        map.put("message", "MY배달지가 등록되었습니다.");
        map.put("code", HttpStatus.ACCEPTED);
        return map;
    }
}    
