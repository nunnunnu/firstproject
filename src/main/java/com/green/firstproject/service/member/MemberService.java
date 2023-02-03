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
import com.green.firstproject.entity.member.MemberCouponEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.member.MyDeliveryEntity;
import com.green.firstproject.repository.member.LatelyDeliveryRepository;
import com.green.firstproject.repository.member.MemberCouponRepository;
import com.green.firstproject.repository.member.MemberInfoReposiroty;
import com.green.firstproject.repository.member.MyDeliveryRepository;
import com.green.firstproject.utils.AESAlgorithm;
import com.green.firstproject.vo.master.CouponVO;
import com.green.firstproject.vo.member.DeliveryVO;
import com.green.firstproject.vo.member.LoginSession;
import com.green.firstproject.vo.member.LoginUserVO;
import com.green.firstproject.vo.member.MemberMypageVO;
import com.green.firstproject.vo.member.MyDeliveryVO;

@Service
public class MemberService {
    @Autowired MemberInfoReposiroty mRepo;
    @Autowired LatelyDeliveryRepository ldRepo;
    @Autowired MyDeliveryRepository mdRepo;
    @Autowired MemberCouponRepository mcRepo;

    public Map<String, Object> addMember(MemberInfoEntity data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getMiEmail()==null && data.getMiPwd()==null && data.getMiPhone()==null){
            resultMap.put("status", false);
            resultMap.put("message", "값이 입력되지 않았습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
            return resultMap;
        }
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
        MemberInfoEntity member = mRepo.findByMiNameAndMiPhone(name, phone);
        if (member!=null) {
                resultMap.put("status", true);
                resultMap.put("message", "회원님의 이메일은"+member.getMiEmail()+" 입니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
        }else{
            resultMap.put("status", false);
            resultMap.put("message", "등록된 회원 정보가 없습니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }

    public Map<String, Object> findPwd(String name, String email){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiNameAndMiEmail(name, email);
        if (member!=null) {
                resultMap.put("status", true);
                resultMap.put("message", "해당 정보와 일치하는 회원이 존재합니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
        }else{
            resultMap.put("status", false);
            resultMap.put("message", "이름 또는 이메일을 확인하여 주십시오.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        return resultMap;
    }
    
    public Map<String, Object> loginMember (LoginUserVO data){
            Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            MemberInfoEntity user = null;
            if(data.getEmail()==null || data.getPwd()==null){
                resultMap.put("status", false);
                resultMap.put("message", "값이 입력되지 않았습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
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
                resultMap.put("status", true);
                resultMap.put("message", "로그인 되었습니다.");
                resultMap.put("code", HttpStatus.ACCEPTED);
                resultMap.put("loginUser", new LoginSession(user));
            }
            return resultMap;
        }
        
        public Map<String, Object> updateMember(Long seq, String pwd, String changePwd, String phone, Integer gen) {
            System.out.println(changePwd);
            Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            if(seq==null){
                resultMap.put("status", false);
                resultMap.put("message", "값이 입력되지 않았습니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            String phonePattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
            String passwordPattern = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$";
            if(changePwd!=null && !Pattern.matches(passwordPattern, changePwd)){ //공백없이 특수문자 가능 6자리 이상
                resultMap.put("status", false);
                resultMap.put("message", "비밀번호는 공백없이 6자리 이상 가능합니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }else if(phone!=null && !Pattern.matches(phonePattern, phone)){
                resultMap.put("status", false);
                resultMap.put("message", "올바른 전화번호 형식이 아닙니다. 번호를 다시 확인해주세요.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            MemberInfoEntity member = mRepo.findByMiSeq(seq);
            System.out.println("before:"+member);
            if (member == null) {
                resultMap.put("status", false);
                resultMap.put("message", "찾을 수 없는 회원번호 입니다.");
                resultMap.put("code", HttpStatus.BAD_REQUEST);
                return resultMap;
            }
            if(phone!=null){
                member.setMiPhone(phone);
            }
            if(gen != null){
                member.setMiGen(gen);
            }
            if(changePwd!=null){
                try{
                    member.setMiPwd(AESAlgorithm.Encrypt(changePwd));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("after:"+member);
            mRepo.save(member);

            resultMap.put("status", true);
            resultMap.put("message", "정보가 변경되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            return resultMap;
        }
        public Map<String, Object> deleteMember(Long seq){
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            MemberInfoEntity member = mRepo.findByMiSeq(seq);
            if (member == null) {
                map.put("status", false);
                map.put("message", "없는 회원번호 입니다.");
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
    public Map<String, Object> memberMypage (Long seq){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiSeq(seq);
        if (member == null) {
            map.put("status", false);
            map.put("message", "찾을 수 없는 회원번호 입니다.");
            map.put("code", HttpStatus.BAD_REQUEST);
            return map;
            }
            MemberMypageVO memberVo = new MemberMypageVO(member);
            map.put("list", memberVo);
            map.put("message", "회원정보 조회완료!");
            map.put("code", HttpStatus.ACCEPTED);
            return map;
            
        }
        
    public Map<String, Object> showMyLatelyDelivery(Long seq) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiSeq(seq);
        List<LatelyDeliveryEntity> list = ldRepo.findByMember(member);

        if(list.size()==0){
            map.put("status", false);
            map.put("message", "최근 주문 내역이 없습니다.");
            map.put("code", HttpStatus.NO_CONTENT);    
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

    public Map<String, Object> showMyDeliveryAddress(Long seq){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiSeq(seq);
        List<MyDeliveryEntity> list = mdRepo.findByMember(member);

        if(list.size()==0){
            map.put("status", false);
            map.put("message", member.getMiEmail()+"님 \n"+"평소에 자주 배달받는 주소를 등록해 보세요.");
            map.put("code", HttpStatus.ACCEPTED);
            return map;
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

    public Map<String, Object> addMyDeliveryAddress(MyDeliveryVO data){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiSeq(data.getSeq());   
        List<MyDeliveryEntity> list = mdRepo.findMember(member);       
        MyDeliveryEntity my = MyDeliveryEntity.builder().mdAddress(data.getAddress()).mdDetailAddress(data.getDetailAddress()).mdName(data.getName()).member(member).mdBasic(data.getBasic()).build();
        if(list.size() > 5){
            map.put("status", false);
            map.put("message", "마이 배달지는 5개 까지 등록 가능합니다.");
            map.put("code", HttpStatus.BAD_REQUEST);
            return map;
        }
        mdRepo.save(my);
        map.put("status", true);
        map.put("message", "MY배달지가 등록되었습니다.");
        map.put("code", HttpStatus.ACCEPTED);
        return map;
    }

    public Map<String, Object> deleteMyDeliveryAddress(Long memberSeq, Long mySeq){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MyDeliveryEntity entity = mdRepo.findByMdSeqAndMember(mySeq, mRepo.findByMiSeq(memberSeq));
        if(entity!=null){
                mdRepo.deleteById(mySeq);
                map.put("status", true);
                map.put("message", "MY배달지가 삭제되었습니다.");
                map.put("code", HttpStatus.ACCEPTED);
        }
        else{
            map.put("status", false);
            map.put("message", "회원 번호를 확인해주세요.");
            map.put("code", HttpStatus.BAD_REQUEST);
        }
        return map;
    }

    public Map<String, Object> updateMyDeliveryName(MyDeliveryVO data, Long seq){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiSeq(data.getSeq());
        MyDeliveryEntity entity = mdRepo.findByMdSeqAndMember(seq, member);
        if(data.getName()!=null && entity!=null){
            entity.setMdName(data.getName());
            mdRepo.save(entity);
            map.put("status", true);
            map.put("message", "MY배달지 별칭이 수정되었습니다.");
            map.put("code", HttpStatus.ACCEPTED);
        }else if( data.getBasic()!=null && entity!=null){
            MyDeliveryEntity basic = mdRepo.findByMemberAndMdBasic(member, 2);
            if(basic!=null){
                basic.setMdBasic(1);
            }
        }
       else{
            map.put("status", false);
            map.put("message", "회원 번호를 확인해주세요.");
            map.put("code", HttpStatus.BAD_REQUEST);
        }
        return map;
    }


    public Map<String, Object> updateMyDeliveryBasic(MyDeliveryVO data, Long seq){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MemberInfoEntity member = mRepo.findByMiSeq(data.getSeq());
        MyDeliveryEntity entity = mdRepo.findByMdSeqAndMember(seq, member);
        if( data.getBasic()!=null && entity!=null){
            MyDeliveryEntity basic = mdRepo.findByMemberAndMdBasic(member, 2);
            if(basic!=null){
                basic.setMdBasic(1);
                entity.setMdBasic(2);
                mdRepo.save(basic);
                mdRepo.save(entity);
                map.put("status", true);
                map.put("message", "기본 배달지가 변경되었습니다.");
                map.put("code", HttpStatus.BAD_REQUEST);
            }
        }
        else{
            map.put("status", false);
            map.put("message", "회원 번호를 확인해주세요.");
            map.put("code", HttpStatus.BAD_REQUEST);
        }
        return map;
    }
    public Map<String, Object> showCoupon(Long seq){
        Map<String, Object> map = new LinkedHashMap<>();

        MemberInfoEntity member = mRepo.findByMiSeq(seq); 
        if(member==null){
            map.put("status", false);
            map.put("message", "회원번호가 잘못되었습니다.");
            map.put("code", HttpStatus.BAD_REQUEST);
            return map;
        }
        List<MemberCouponEntity> memberCoupon = mcRepo.findByMember(member);
        List<CouponVO> couponList = new ArrayList<>();
        if(memberCoupon.size()!=0){
            for(MemberCouponEntity m : memberCoupon){
                CouponVO coVo = new CouponVO(m);
                couponList.add(coVo);
            }
            map.put("status", true);
            map.put("message", "쿠폰리스트를 조회하였습니다.");
            map.put("code", HttpStatus.ACCEPTED);
            map.put("coupon", couponList);
        }else{
            map.put("status", false);
            map.put("message", "보유중인 쿠폰이 없습니다.");
            map.put("code", HttpStatus.NO_CONTENT);
        }    
        return map;
    }
}