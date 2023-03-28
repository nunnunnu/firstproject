# 음식배달 서비스 - 버거왕 프로젝트

기간: 2022년 12월 28일 → 2023년 2월 6일
사용기술: Gradle, JPA, bootstrap, git, java, mysql, spring boot
작성 날짜: 2022년 12월 26일 오후 4:33
작업인원: 7
태그: 협업 프로젝트

- 총 작업 인원 : 7명 (백엔드 - 4명/ 프론트엔드 - 3명)
    - 백엔드 : **박진희**, ****정인원, 박정은, 유지은
    - 프론트엔드 : 최금옥(팀장), 김재진, 윤동규

<aside>
🍔 음식 주문 서비스 - 버거왕(벤치마킹한 사이트 : 버거킹)
버거킹 딜리버리 사이트를 벤치마킹하여 진행한 프로젝트입니다. 버거킹 딜리버리 사이트의 모든 기능을 구현하려 노력했습니다.
프론트엔드 팀에서 약 2주정도 늦게 참여하게되어 프론트엔드 팀의 업무를 분담하고자 백엔드팀에서 mvc패턴을 이용한 관리자 사이트를 구현했습니다.

</aside>

### 프로젝트 깃허브 주소

https://github.com/inwonjung/firstproject.git

### PPT 주소

[](https://www.canva.com/design/DAFZONDccqE/Ledx98kAYBzpUJB8rLYDmg/edit?utm_content=DAFZONDccqE&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

- [정규화/테이블명세서/API명세서/간트차트 파일 (엑셀파일)](https://www.notion.so/45d2c034984147dab5fa7ec67c241758)
    
    [버거킹 (2).xlsx](%E1%84%8B%E1%85%B3%E1%86%B7%E1%84%89%E1%85%B5%E1%86%A8%E1%84%87%E1%85%A2%E1%84%83%E1%85%A1%E1%86%AF%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20-%20%E1%84%87%E1%85%A5%E1%84%80%E1%85%A5%E1%84%8B%E1%85%AA%E1%86%BC%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%2045d2c034984147dab5fa7ec67c241758/%25EB%25B2%2584%25EA%25B1%25B0%25ED%2582%25B9_(2).xlsx)
    

## 시연 동영상

### 프론트 구현

### 백엔드 구현(관리자페이지)

[https://youtu.be/-8-XVJf_cDs](https://youtu.be/-8-XVJf_cDs)

[https://youtu.be/snW6NWhX5_A](https://youtu.be/snW6NWhX5_A)

[구현 기능](https://www.notion.so/e1ffc30c1b7b46ea96f7d3f5185f73c8)

---

[업무일지](https://www.notion.so/da1e36c3ba8546a596374a686eaa107a)

[회의](https://www.notion.so/189b61b04f1841a38882f3a042bb6eea)

---

### 후기

테이블 구조를 짤 때 구현 클래스마다 테이블을 만들었는데 기본키번호가 각각 따로 존재해서 프론트에서 혼란스러워했다. 다음에 비슷한 구조가 있으면 상속관계 매핑전략을 사용해야할거같다.

프로젝트 완성 직전에 만든 기능들은 테스트를 완벽하게 하지못해서 시연때 회원가입에서 오류가 났다. 프론트에서 회원가입 시 생년월일 값을 넘겨줄때 선택하지 않으면 “--”, 한자리 수를 선택하면 ‘2023-1-1’ 이런식으로 값이 날아오는 것을 보고 코드를 수정했다. 다음에는 더 꼼꼼히 체크를 해야하겠다고 다짐했다

[이력서 페이지로 돌아가기](https://www.notion.so/Jinhee-Park-60fef02523c1491ba4fdfee4a4562252)