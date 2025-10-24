# 2025.10.20 
---
## User - 쿠키처리, Bcrypt

https://docs.google.com/spreadsheets/d/1AAli0Zi_GWo2G4y83B9dWc0CgpluyUcxIAHhjl-l3lc/edit?usp=sharing


## [1] 회원가입
/api/user/signUp
POST
```
{
"uid":"qwer1234",
"upwd":"qwer1234",
"uname":"배두훈",
"uphone":"010-0000-0000",
"urole":"USER"
}
```
성공 시 PK 반환

## [2] 로그인
/api/user/login
POST
```
{
"uid":"qwer1234",
"upwd":"qwer1234"
}
```
성공 시 not null/ 실패 시 not content = null

## [3] 내정보조회
/api/user/info
GET

# 2025.10.21
---
X


# 2025.10.22
---
https://docs.google.com/spreadsheets/d/1BULalFBZN75Lg-2BD8crZeYcHv2bk3iWp39y78nFL8Q/edit?usp=sharing

## JWT 

### 정의
- JSON Web Token
- 웹어서 자바스크립트 기반의 특정한 데이터를 대신하는 징표
- 특정한 데이터를 직접적으로 보여주지 않고 토큰을 대신 보여줌

- 같은 데이터에 대해 서로 다른 토큰이 발생하도록 하는 알고리즘 추가 필요
- SHA-256 알고리즘을 사용하여 비밀키(알고리즘)를 32글자 이상으로 생성해야 함

### build.gradle에 dependencies 추가
```gradle
    implementation 'io.jsonwebtoken:jjwt-api:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'
```

#### 토큰 생성 test
/api/jwt/create?data="사과"
Get
#### 토큰 확인
/api/jwt/check?token=
Get
#### 토큰 추출
/api/jwt/payload?token=
Get

user 에 토큰 기능 주입

## Spring security
---

- 주의 Spring security 로 인해서 로그인/API 호출 등으 모두 막힘!!!

- gradle 추가 후 실행하면 
- console에 password 가 발급됨
- id : user / pwd : 발급된 pwd

# 2025.10.23
---
https://docs.google.com/spreadsheets/d/1BULalFBZN75Lg-2BD8crZeYcHv2bk3iWp39y78nFL8Q/edit?usp=sharing

## 소셜 로그인

- 카카오, 네이버 : 개인정보 포함 - 사업자번호 인증필요 > 실습 어려움
- 구글, 페이스북 : 개인정보 미포함

# 2025.10.24
---
https://docs.google.com/spreadsheets/d/1XjWUC6gTDQniAJkOz2PQ2ekryl6jWroGGYiG1KXbe-k/edit?gid=541518987#gid=541518987