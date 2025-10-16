USE springweb2;

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    dept_id INT,
    salary INT,
    hire_date DATE,
    email VARCHAR(100)
);
-- 부서 테이블 생성
DROP TABLE IF EXISTS department;
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50)
);

-- 샘플 데이터
INSERT INTO department VALUES
(1, '개발팀'),
(2, '기획팀'),
(3, '디자인팀');

INSERT INTO employee (name, dept_id, salary, hire_date, email) VALUES
('유재석', 1, 5000, '2023-01-10', 'yu@test.com'),
('강호동', 2, 4000, '2024-02-12', 'kang@test.com'),
('신동엽', 1, 7000, '2022-06-05', 'shin@test.com'),
('이수근', 2, 5500, '2025-03-22', 'lee@test.com'),
('하하', 3, 3500, '2025-04-10', 'haha@test.com'),
('정형돈', 1, 6200, '2023-07-11', 'don@test.com'),
('박명수', 2, 4800, '2023-09-02', 'park@test.com'),
('노홍철', 3, 3700, '2024-05-14', 'noh@test.com'),
('김종국', 1, 8000, '2022-11-01', 'kim@test.com'),
('양세형', 2, 4300, '2024-06-21', 'yang@test.com'),
('이광수', 3, 3900, '2023-12-12', 'kwang@test.com'),
('조세호', 1, 5100, '2023-03-18', 'cho@test.com'),
('김용만', 2, 4600, '2022-09-23', 'yong@test.com'),
('정준하', 3, 3600, '2024-04-04', 'jun@test.com'),
('김태호', 1, 9000, '2021-10-15', 'taeho@test.com'),
('서장훈', 2, 5800, '2024-08-25', 'seo@test.com'),
('전현무', 3, 4000, '2022-12-01', 'jeon@test.com'),
('김구라', 1, 7500, '2023-11-05', 'gura@test.com'),
('유병재', 2, 4200, '2025-01-20', 'yoo@test.com'),
('김민아', 3, 3800, '2024-10-08', 'mina@test.com');

-- 인덱스
-- 데이터를 빠르게 검색하기 위한 색인
-- PK는 기본적인 인덱스를 갖음 : 테이블 1개당 PK 1개는 권장
-- 관계형 DB 구조상 특정한 데이터를 찾을 때 검색기준·인덱스를 미리 지정하면 빠름

-- [1] primary key는 기본적인 인덱스를 갖음
select * from employee where id = 1;

-- [2] 인덱스 목록 조회
-- show index from 테이블;
show index from employee;

-- [3] 단일 컬럼 인덱스 생성
-- create index 인덱스먕 on 테이블명(필드명);
create index idx_name on employee(name);

-- [4] 조회 쿼리 성능
-- select 앞에 
-- explain analyze
explain analyze select * from employee where name = "유재석";

-- [5] index 삭제
-- drop index 인덱스명 on 테이블명;
drop index idx_name on employee;

-- [6] 복합 인덱스 생성
-- create index 인덱스 on employee( 필드명, 필드명 );
create index idx_salary on employee (dept_id, salary);

-- 주의!! 복합 인덱스의 경우, 첫번째 index 에 대해서 단일 사용이 가능
explain analyze select * from employee where dept_id=1;

-- 두번째 index에 대해서는 index가 적용되지 않음
explain analyze select* from employee where salary = 7000;

-- 같이 검색하는 것은 index 적용
explain analyze select * from employee where dept_id = 1 and salary = 7000;

-- [7] FK에 인덱스 추가후 Join 하면 속도가 증가함
-- FK 에 index 추가
create index idx_dept on employee(dept_id);
-- join
select * from employee e inner join department d on e.dept_id = d.dept_id;

-- [8] 문자열 검색 fulltext 인덱스
-- 검색 시 자연어를 기계어롤 바꾸지 않고 자연어 자체로 검색하므로 속도가 증가함
-- 자연어 : 사람이 사용하는 언어 VS 기계어 : 컴퓨터가 처리하는 언어·2진수
-- 타입이 text, char, varchar 일 때 사용 가능
create fulltext index index_name_full on employee(name);

-- 자연어 검색방법
-- select * from employee where match(속성명) against('값');
select * from employee where match(name) against('유재석');