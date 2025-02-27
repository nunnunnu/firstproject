[노션으로 보기](https://coordinated-sunset-4f9.notion.site/45d2c034984147dab5fa7ec67c241758)
# 음식배달 서비스 - 버거왕 프로젝트

기간: 2022년 12월 28일 → 2023년 2월 6일

사용기술: Gradle, JPA, bootstrap, git, java, mysql, spring boot

- 총 작업 인원 : 7명 (백엔드 - 4명/ 프론트엔드 - 3명)
    - 백엔드 : **박진희**, (팀장)정인원, 박정은, 유지은
    - 프론트엔드 : 최금옥(팀장), 김재진, 윤동규

<aside>
🍔 음식 주문 서비스 - 버거왕(벤치마킹한 사이트 : 버거킹)
버거킹 딜리버리 사이트를 벤치마킹하여 진행한 프로젝트입니다. 버거킹 딜리버리 사이트의 모든 기능을 구현하려 노력했습니다.
프론트엔드 팀에서 약 2주정도 늦게 참여하게되어 프론트엔드 팀의 업무를 분담하고자 백엔드팀에서 mvc패턴을 이용한 관리자 사이트를 구현했습니다.

</aside>

## 구현 기능

|이름|기여도|태그|설명|
|------|-----|-----|---|
|쿠폰 사용처리	|100	|쿠폰	|주문 후 사용한 쿠폰을 사용처리해서 사용불가능하도록 처리
|쿠폰 조회	|100	|쿠폰	|사용가능한 쿠폰과 불가능한 쿠폰(이미사용/기간만료) 조회
|판매불가 메뉴 주문 막기	|100	|주문	|지점에서 판매불가 메뉴로 등록했을 시 주문을 못하도록 막음
|영업시간 체크	|100	|주문	|주문 시 매장이 영업시간이 아닐때 주문을 막음
|구매 시 판매량 증가	|100	|주문	|구매 수량만큼 해당 메뉴의 판매량을 늘림
|최근 배송지 조회	|100	|배송지	|최근에 회원이 배송받은 배송지를 조회
|결제 수단 조회	|100	|주문	|결제가능한 수단을 모두 조회
|주문 리스트 조회	|100	|주문	|회원이 주문한 리스트를 조회
|주문 상세 조회	|100	|주문	|특정 주문 조회기능
|카테고리 조회	|50	|메뉴	|판매가능한 메뉴를 조회함. 랭킹 기능 등 쿼리문 작성을 맡음
|주문 취소	|100	|주문	|고객이 주문한 메뉴가 취소가능한 상태(접수/주문확인/배송중)일때만 취소가능
|관리자 페이지	|100	|프론트	|부트스트랩을 이용해서 데이터를 입력할 관리자 페이지 구현
|마이배달지 등록	|30	|배송지	|구현 로직을 어떻게하면되는지 팀원에게 설명/도움

## 시연 동영상

### 프론트 구현
[![Video Label](http://img.youtube.com/vi/-8-XVJf_cDs/0.jpg)]
(https://youtu.be/-8-XVJf_cDs)
### 백엔드 구현(관리자페이지)
[![Video Label](http://img.youtube.com/vi/snW6NWhX5_A/0.jpg)]
(https://youtu.be/snW6NWhX5_A)

---
### 기타 주소

[업무일지(노션 페이지)](https://coordinated-sunset-4f9.notion.site/da1e36c3ba8546a596374a686eaa107a?v=825104263b974533a2ae9d84edb26ea2)

[회의록(노션 페이지)](https://coordinated-sunset-4f9.notion.site/189b61b04f1841a38882f3a042bb6eea?v=80280550f98d46c8bd390afdbf72af35)

[ppt 링크](https://www.canva.com/design/DAFZONDccqE/Ledx98kAYBzpUJB8rLYDmg/edit?utm_content=DAFZONDccqE&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

[정규화/테이블명세서/API명세서/간트차트 파일 (엑셀파일)](https://drive.google.com/file/d/1IZ9Ya9FhEj7FKt3GqUKQ7pjgUVCU_zdP/view?usp=share_link)
    

