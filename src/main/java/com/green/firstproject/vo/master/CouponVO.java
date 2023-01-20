package com.green.firstproject.vo.master;

import java.time.LocalDate;

import com.green.firstproject.entity.member.MemberCouponEntity;

import lombok.Data;

@Data
public class CouponVO {
     private Long couponSeq;
     private String couponName;
     private Integer couponPrice;
     private Boolean couponAvailability;

     public CouponVO(MemberCouponEntity coupon){
          this.couponSeq=coupon.getCoupon().getCiSeq();
          this.couponName = coupon.getCoupon().getCiName();
          this.couponPrice = coupon.getCoupon().getCiDiscount();
          if(coupon.getMcUse() || //이미 사용했거나
               (coupon.getMcDate().getYear()!=LocalDate.now().getYear() //쿠폰 발급일자의 년도가 다르고
               &&coupon.getMcDate().getMonth()!=LocalDate.now().getMonth()) //쿠폰 발급일자의 월이 다를때
          ){
               this.couponAvailability=true;
          }else{
               this.couponAvailability=false;
          }
     }
}
