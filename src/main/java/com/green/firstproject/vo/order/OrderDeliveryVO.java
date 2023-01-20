package com.green.firstproject.vo.order;

import java.util.ArrayList;
import java.util.List;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.member.MemberInfoEntity;
import com.green.firstproject.entity.order.cart.CartDetail;
import com.green.firstproject.entity.order.cart.CartVo;
import com.green.firstproject.vo.master.CouponVO;

import lombok.Data;

@Data
public class OrderDeliveryVO {
     private String address;
     private String userPhone;
     private String storeName;
     private List<CartVo> orderMenus;
     private List<CouponVO> coupon;
     private Integer totalPrice;

     public OrderDeliveryVO(String address, MemberInfoEntity member, StoreInfoEntity store){
          this.address=address;
          this.userPhone=member.getMiPhone();
          this.storeName = store.getSiName();
          orderMenus = new ArrayList<>();
          coupon = new ArrayList<>();
          this.totalPrice=0;
     }

     public void orderMenuSetting(List<CartVo> list){
          orderMenus.addAll(list);
     }


     public void couponSetting(List<CouponVO> coupon){
          this.coupon.addAll(coupon);
     }

     public void addPrice(CartDetail c){
          this.totalPrice+=c.getPrice();
     }
     public void addPrice(CartVo c){
          this.totalPrice+=c.getPrice();
     }

}
