use springweb2;

-- [1] 테이블 생성

drop table if exists employee;
create table employee(
	id int,
    name varchar(50),
    dept varchar(50)
);

-- [2] ALTER 기존 테이블의 필드 추가
-- alter talbe 테이블명 drop column 속성명
alter table employee add column age int default 10;

alter table employee add column date Date; 

-- [3] 기존 테이블의 필드 덩보 수정
-- alter table 테이블명 modify column 수정필드명 새로운타입;
alter table employee modify dept longtext;

-- [4] 기존 테이블의 필드명 수정
-- alter table 테이블명 change column 기존필드명 새로운필드명 타입;
alter table employee change column name nickname varchar(100);

-- [5] 기존 테이블의 필드 삭제
-- alter table 테이블명 drop column 삭제필드명;
alter table employee drop column date;

-- [6] 테이블의 필드 정보 확인
-- 메타데이터 확인용이며 show는 ddl dml dcl 어디에도 포함되지 않음
show columns from employee;

-- [7] 제액조건 추가
-- alter table 테이블명 add constraint [제약조건명] 제약조건 ;
-- alter table 테이블명 add constraint [제약조건명] foreign key(fk 필드명) references 참조테이블명(PK필드명);
alter table employee add constraint employee_pk primary key(id); -- PK가 여러 개이면 PK명 설정이 가능하지만 PK가 하나면 PK명이 부여되지 않음
alter table employee add constraint employee_namce unique(name);

-- [8] 제약 조건 삭제
-- alter table 테이블명 drop constraint 제약조건명
-- alter table 테이블명 drop primary key;	--PK삭제 
-- alter table 테이블명 drop foreign key 삭제할FK명;	-- FK 삭제
alter table employee drop constraint employee_pk ; -- 실행 안되는 게 맞음
alter table employee drop constraint employee_name;

alter table employee drop primary key;


-- [9] 수정 없이 삭제 후 다시 제약조건 추가

-- [10] 제약조건 확인
-- 현재 스키마의 모든 project 및 table에 대한 확인
select * from information_schema.table_constraints;

select * from information_schema.table_constraints where table_schema = "springweb2";

-- 특정 table의 제약조건 확인
select * from information_schema.table_constraints where table_schema = "springweb2" and table_name = "employee";


select * from employee;


