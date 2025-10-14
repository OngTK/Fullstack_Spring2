# 251014_Practice4

### 1. 단일 도서 등록
POST
http://localhost:8080/book

```
{
"title" : "test 책",
 "stock" : 3
}
```

### 2. 대출 기록 검색
GET
http://localhost:8080/book/rental?member=길&title=바

### 3. 도서 일괄 등록
POST
http://localhost:8080/book/sever
```
[
{
"title" : "test 책1",
 "stock" : 4
},{
"title" : "test 책2",
 "stock" : 5
},{
"title" : "test 책3",
 "stock" : 6
}
]
```