-- DCL : Database Control Language
-- 계정관리, 계정의 사용권한 관리

-- [1] 계정 만들기 :  root(최고 관리자) 계정으로 가능
-- DB를 설치하면 기본적으로 root 계정이 존재 

-- ` create user '계정명'@'허용도메인' identified by '비밀번호' `
-- ` create user '계정명'@'%' identified by '비밀번호' ` : 와일드카드
-- ` create user '계정명'@'192.XX.XX.XX' identified by '비밀번호' ` : 특정 IP에서 계정으로 접근 가능
create user 'dev1'@'localhost' identified by '1234';

create user 'dev2'@'%' identified by 'abcd'; -- 해당 아이디가 어느 도메인이든 접속을 허용함

-- [2] 다른 계쩡으로 접속하기 (Workbanch기준)
-- Workbanch home > connection > + 버튼 클릭
-- username : 계정명 / password : 비밀번호 > test connection > ok

-- [3] 권한 부여 GRANT 

-- 모든 권한 부여
-- GRANT ALL privileges ON DB명.테이블 TO '계정명'@'도메인';

GRANT ALL privileges ON SPRINGWEB2.* TO 'dev1'@'localhost';

grant select on springweb2.student to 'dev2'@'%';

-- 권환 확인
-- show grants for '계정명'@'허용도메인';
show grants for 'dev1'@'localhost';

-- [4] 권한 취소 REVOKE
-- revoke select on DB명.테이블명 from '계정명'@'도메인';
revoke select on springweb2.student from 'dev2'@'%';


-- [5] 계정 비밀번호 변경
-- alter user '계정명'@'허용도메인' identified by '새로운비밀번호';

alter user 'dev2'@'%' identified by '1234';

-- [5] 계정 삭제
-- drop user '계정명'@'도메인';
drop user 'dev2'@'%';