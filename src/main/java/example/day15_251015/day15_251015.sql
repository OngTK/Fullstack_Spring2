use springweb2;

set SQL_SAFE_UPDATES = 0;

-- 트랜잭션
-- 여러 작업들을 하나의 묶음으로 간주하여 모두 성공하면 commit, 
-- 하나라도 실패하면 rollback

-- MySQL wrokbanch의 자동 Commit 비활성화
set autocommit = 0;

-- [1] 트랜잭션 시작
start transaction;

-- [2] 여러 작업
-- DML : Insert / update / delete
-- [2.1] 출금
update trans set money = money - 30000 where id = 1;
-- [2.2] 입금
update trans set money = money + 30000 where id = 2;


-- [3.1] 취소 rollback
rollback;
-- [3.2] 완료 commit
commit;

-- [4] 확인
select * from trans;

-- ----------------------------------------------------------
-- 
-- [1] 트랜잭션 시작
start transaction;

-- [2] 작업
-- [2.1] 출금
update trans set money = money - 30000 where id = 1;

-- [3] 저장 지점
savepoint pointA; -- 저장 지점

-- [2.2] 입금
update trans set money = money - 30000 where id = 2;

-- [4] 완료
commit;

-- [4.2] 특정시점으로 rollback
rollback to pointA;

-- [5] 확인
select * from trans;

-- DCL : data controll language
-- 권한부여 및 트랜잭션

-- TCL : transaction control language
-- commit, rollback, savepoint

-- 1. Java Spring (@Transactional 사용하되 RuntimeException으로 롤백, savepoint를 지원하지 않음)
-- 2. Java JDBC (DAO)는 3개의 기능을 모두 지원

-- --------------------------------------------------------
-- savepoint를 다중으로 사용

start transaction;
update trans set money = money - 10000 where id = 1;
savepoint s1;
update trans set money = money - 10000 where id = 2;
savepoint s2;
update trans set money = money - 30000 where id = 2;
savepoint s3;

rollback to s1;
rollback to s2;
rollback to s3;

commit;

select * from trans;

-- 실무 : 