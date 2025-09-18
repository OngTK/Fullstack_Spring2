USE springweb2;

drop table if exists members;
create table members(
	mno int PRIMARY KEY AUTO_INCREMENT,
    name varchar(30) not null,
    phone varchar(15) not null,
    age int not null
);

insert into members(name, phone, age) values ("배두훈","010-0000-0000",40);
insert into members(name, phone, age) values ("강형호","010-0000-1111",38);
insert into members(name, phone, age) values ("조민규","010-0000-2222",36);
insert into members(name, phone, age) values ("고우림","010-0000-3333",31);

select * from members;