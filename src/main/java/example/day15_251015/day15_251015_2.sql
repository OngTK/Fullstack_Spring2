use springweb2;

-- 1.기존 테이블 초기화
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS member;

-- 2.회원(member) 테이블 생성
CREATE TABLE member (
    mno INT AUTO_INCREMENT PRIMARY KEY,   -- 회원번호 (PK)
    name VARCHAR(50),                     -- 이름
    grade VARCHAR(20)                     -- 등급 (VIP, GOLD, SILVER)
);

-- 3. 주문(orders) 테이블 생성
CREATE TABLE orders (
    ono INT AUTO_INCREMENT PRIMARY KEY,   -- 주문번호 (PK)
    mno INT,                              -- 회원번호 (FK)
    product VARCHAR(50),                  -- 상품명
    price INT,                            -- 가격
    order_date DATE,                      -- 주문일자
    FOREIGN KEY (mno) REFERENCES member(mno)
);

-- 4. 샘플 데이터 삽입
INSERT INTO member (name, grade)
VALUES ('유재석', 'VIP'), ('강호동', 'GOLD'), ('신동엽', 'SILVER');

INSERT INTO orders (mno, product, price, order_date)
VALUES
(1, '노트북', 1500000, '2025-10-10'),
(1, '마우스', 30000, '2025-10-11'),
(2, '키보드', 50000, '2025-10-11'),
(3, '모니터', 200000, '2025-10-12');

-- -------------------------------------------------------------
select * from member;


-- View 가상 테이블
-- 하나 이상의 원본 테이블들을 기반으로 만들어진 가상 테이블

-- 권한, 보안, 복잡한 쿼리문(통계, 통계) 결과 저장 등 재사용

-- [1] view 생성
select * from orders;

-- create or replace view 뷰이름 as 뷰테이블;
-- create or replace view 뷰이름 as select문;

create or replace view order_test as select * from orders;

-- [2] 뷰 목록 확인
show full tables where table_type = "VIEW";

-- [3] 뷰 수정
-- alter view 뷰이름 as 쿼리문;
alter view order_test as select product, price from orders;

-- [4] 뷰 조회
select * from order_test;

-- [5] 뷰 삭제
drop view if exists order_test;

-- 뷰 주의점
-- 		: 특별한 경우가 아니라면 select 만 가능 / update/delete/insert는 조건적으로만 사용
-- 		: view 는 주로 읽기모드로만 이용
-- 		: 특히 join, group by, 집계, 통계 등 데이터가 합쳐져 있는 경우, 당연히 R만 가능

-- 		: 단일 테이블 기반의 view는 insert/delete/update 가능


-- -------------------------------------------------------------------

select * from member;
select * from member where grade = "VIP";

-- [1] VIP view 만들기
create or replace view vip_member as
select * from member where grade = "VIP";

select * from vip_member ;

-- [2] view 데이터 수정 O / 단일테이블 기반
update vip_member set name = '유재석2';

-- ---------------------------------------------------------------------
-- join view 생성
select * from member m inner join orders o on m.mno = o.mno;

create or replace view view_order_subbary as
select m.*, o.ono, o.product, o.price, o.order_date  from member m inner join orders o on m.mno = o.mno;

select * from view_order_subbary;

-- v
update view_order_subbary set name = "유재석3";


-- ----------------------------------------------------------------------
-- 뷰의 
create or replace view vip_member2 as  
select *, 10+10 as 계산 from member where grade = "vip";

select * from vip_member2;

update vip_member2 set 계산 = 30;  -- 불가능!!!
update vip_member2 set name = '유재석2'; -- 가능!!!
-- 단일 테이블의 원본 속성 수정은 가능
-- 집계/통계/그룹/계산 등등 필드에 대해서는 수정 불가