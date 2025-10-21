# 2025.10.20 

https://docs.google.com/spreadsheets/d/1AAli0Zi_GWo2G4y83B9dWc0CgpluyUcxIAHhjl-l3lc/edit?usp=sharing
---

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

##