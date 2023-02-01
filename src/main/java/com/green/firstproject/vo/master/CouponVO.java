package com.green.firstproject.vo.master;

import java.time.LocalDate;

import com.green.firstproject.entity.member.MemberCouponEntity;

import lombok.Data;

@Data
public class CouponVO {
     private Long seq;
     private String name;
     private Integer price;
     private Boolean availability;

     public CouponVO(MemberCouponEntity coupon){
          this.seq=coupon.getMcSeq();
          this.name = coupon.getCoupon().getCiName();
          this.price = coupon.getCoupon().getCiDiscount();
          if(!coupon.getMcUse() && //사용하지 않았고
               coupon.getMcDate().getYear()==LocalDate.now().getYear() //쿠폰 발급일자의 년도가 같고
               &&coupon.getMcDate().getMonth()==LocalDate.now().getMonth() //쿠폰 발급일자의 월이 같을때
          ){
               this.availability=true;
          }else{
               this.availability=false;
          }
     }
}
