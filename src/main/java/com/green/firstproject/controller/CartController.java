// package com.green.firstproject.controller;

// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.lang.Nullable;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.green.firstproject.entity.master.StoreInfoEntity;
// import com.green.firstproject.entity.order.cart.CartDetail;
// import com.green.firstproject.repository.master.StoreInfoRepository;
// import com.green.firstproject.repository.member.MemberInfoReposiroty;
// import com.green.firstproject.service.order.OrderService;
// import com.green.firstproject.service.order.cart.CartService;
// import com.green.firstproject.vo.order.cart.AddCart;
// import com.green.firstproject.vo.order.cart.UpdateCartVO;

// import jakarta.servlet.http.HttpSession;
// @RestController
// @RequestMapping("/cart")
// public class CartController {

//     @Autowired CartService cartService;
//     @Autowired MemberInfoReposiroty mReposiroty;
//     @Autowired StoreInfoRepository sRepository;
//     @Autowired OrderService orderService;

//     @PutMapping("")
//     public ResponseEntity < Object > putCart(
//         HttpSession session,
//         @RequestBody AddCart cartInfo
//     ) {
//         /* 나중에 밑에꺼 바꿔야함 */
//         StoreInfoEntity store = sRepository.findAll().get(0); //일단 선택 매장 고정

//         Map < String, Object > map = new LinkedHashMap < > ();
//         if (session.getAttribute("loginUser") == null) {
//             map.put("status", false);
//             map.put("message", "로그인을 먼저 해주세요.");
//             map.put("code", HttpStatus.BAD_REQUEST);
//         }
//         if (LocalTime.now().isBefore(store.getSiOpenTime()) || LocalTime.now().isAfter(store.getSiCloseTime())) {
//             map.put("status", false);
//             map.put("message", "현재 선택된 매장은 영업시간이 아닙니다. " + store.getSiOpenTime() + "~" + store.getSiCloseTime() + "사이에 주문해주세요.");
//             map.put("code", HttpStatus.BAD_REQUEST);
//             return new ResponseEntity < > (map, (HttpStatus) map.get("code"));
//         }

//         map = cartService.addCart(cartInfo.getMenu(), cartInfo.getEvent(), cartInfo.getSide(), cartInfo.getDrink(), cartInfo.getDrink2(), cartInfo.getIngredients(), cartInfo.getCount());
//         if (map.get("cart") == null) {
//             return new ResponseEntity < > (map, (HttpStatus) map.get("code"));
//         }
//         Map < String, Object > stockMap = cartService.stockCheck((CartDetail) map.get("cart"), store);
//         if (!(Boolean) stockMap.get("status")) { //재고가 없다면
//             return new ResponseEntity < > (stockMap, (HttpStatus) stockMap.get("code"));
//         }

//         List < CartDetail > carts = (List < CartDetail > ) session.getAttribute("cart");
//         if (carts == null) {
//             carts = new ArrayList < > ();
//         }
//         carts.add((CartDetail) map.get("cart"));
//         session.setAttribute("cart", carts);
//         map.remove("cart");

//         return new ResponseEntity < > (map, (HttpStatus) map.get("code"));
//     }
    
//     @GetMapping("/list")
//     public ResponseEntity < Object > showCart(HttpSession session) {
//         List < CartDetail > carts = (List < CartDetail > ) session.getAttribute("cart");

//         Map < String, Object > map = cartService.showCart(carts);

//         return new ResponseEntity < > (map, (HttpStatus) map.get("code"));
//     }
//     @PatchMapping("/list/{type}/{seq}")
//     public ResponseEntity < Object > updateCart(HttpSession session,
//         @PathVariable String type,
//         @PathVariable Long seq,
//         @RequestBody @Nullable UpdateCartVO updateCart
//     ) {
//         Map < String, Object > map = new LinkedHashMap < > ();
//         List < CartDetail > carts = (List < CartDetail > ) session.getAttribute("cart");
//         if (carts == null) {
//             map.put("status", false);
//             map.put("message", "카트에 담긴 메뉴가 없습니다.");
//             map.put("code", HttpStatus.BAD_REQUEST);
//             return new ResponseEntity < > (map, (HttpStatus) map.get("code"));
//         }
//         CartDetail cart = cartService.findCart(carts, seq);
//         if (cart == null) {
//             map.put("status", false);
//             map.put("message", "수정할 카트번호를 잘못 입력하셨습니다.");
//             map.put("code", HttpStatus.BAD_REQUEST);
//             return new ResponseEntity < > (map, (HttpStatus) map.get("code"));
//         }

//         if (type.equals("count") && updateCart.getCnt() != null) {
//             map = cartService.cartCountChange(cart, updateCart.getCnt());
//         } else if (type.equals("option")) {
//             map = cartService.cartOptionChange(cart, updateCart.getSide(), updateCart.getDrink(), updateCart.getDrink2(), updateCart.getIngredients());
//         } else if (type.equals("delete")) {
//             map = cartService.cartMenuDelete(carts, seq);
//         } else {
//             map.put("status", false);
//             map.put("message", "주소를 잘못 입력하셨습니다. {예시: count, option, delete}");
//             map.put("code", HttpStatus.ACCEPTED);
//         }

//         return new ResponseEntity < > (map, (HttpStatus) map.get("code"));
//     }
// }