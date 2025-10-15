use springweb2;

-- [1]select 중첩하여 사용하는 서브쿼리

-- [1.1] 일반 select
select * from student;

select avg( (math + kor) / 2 ) 평균점수 from student;

-- [1.2] 중첩 select
-- select [서브쿼리] from [서브쿼리] where [서브쿼리];
-- 평균보다 높은 점수의 학생명
select name from student 
where (math+kor)/2 > ( select avg( (math + kor) / 2 ) 평균점수 from student );

-- [2] 국어 점수가 평균 이상인 학생들과 같은 점수를 가진 학생 조회
-- [2.1] 서브 쿼리 구성
select avg(kor) from student; -- 국어 점수 평균

select kor from student where kor >= (select avg(kor) from student); -- 평균 국어 점수보다 높은 국어 점수들을 조회

select name from student 
	where kor 
    in ( select kor from student 
    where kor >= (select avg(kor) from student) 
    );
    
-- [3] 서븝쿼리를 이용한 총점과 학생 비교
select s1.name, 
	( select count(*) from student s2 where (s2.kor+s2.math) > (s1.kor+s1.math))+1 
from student s1;


-- [4] tjqmznjflfh vudrbs wjatn wjdfuf 
select name,평균 from (select name, (kor+math)/2 as 평균 from student) as 평균테이블;
