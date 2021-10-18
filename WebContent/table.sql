drop table customer;
drop table address;
create table customer(
/*회원가입 폼에 있음*/
c_id varchar(45) primary key,
c_grade nvarchar(10) not null,/*회원가입 폼에서 제외*/
c_password varchar(256) not null,
c_name nvarchar(20) not null,
c_gender char(1) not null,
c_birth datetime not null,
c_email1 varchar(45),
c_email2 varchar(45),
c_tel varchar(40) not null,
c_joindate datetime not null default now(),/*회원가입 폼에 없음*/
order_quantity int not null default 0,
order_money int default 0
);

create table address(
addr_index int auto_increment primary key,/*auto_increment하려면 primary key*/
c_id varchar(45) not null,
postcode int not null,/*우편번호*/
address1 Nvarchar(60) not null,
address2 Nvarchar(60) not null
);

drop table order_car;
create table order_car(
order_no int auto_increment primary key,
c_id varchar(45) not null,
car_no int not null,
rental_date date not null,
return_date date not null,
rental_price int not null
);
alter table order_car auto_increment=1;


drop table driver_detail;
create table driver_detail (
c_id varchar(45) not null,
c_name nvarchar(20) not null,
c_birth datetime not null,
c_tel varchar(40) not null,
rental_place1 nvarchar(20) not null,
rental_place2 nvarchar(20) not null,
rental_place3 nvarchar(20) not null,
return_place1 nvarchar(20) not null,
return_place2 nvarchar(20) not null,
return_place3 nvarchar(20) not null,
request nvarchar(200)
);
select * from driver_detail;
select * from order_car;

drop table drive_lic;
create table drive_lic(
c_id varchar(45),
c_name varchar(45),
lic_info nvarchar(20),
lic_area nvarchar(20),
lic_num int
);

select * from rentcar;

truncate rentcar;
create table rentcar(
car_no int(7) auto_increment not null primary key,
car_name nvarchar(20),
car_group nvarchar(10),
car_year int,
car_reserve char(1),
car_price int,
car_brand nvarchar(15),
car_image varchar(20),
car_readCount int
);

alter table rentcar
add foreign key (customer_no) references customer(customer_no);

alter table rentcar
add foreign key (car_no) references car(car_no);


alter table rentcar auto_increment=1;

insert into rentcar values(null,'k3','중소형', 2022, 'y', 20000, '기아', 'k3.jpg', 0);
insert into rentcar values(null, 'mornig','경형', 2022, 'y', 20000, '기아', 'morning.jpg', 0);
insert into rentcar values(null, 'avante','중소형', 2022, 'y', 20000, '현대', 'avante.jpg',0);
insert into rentcar values(null, 'AUDI','수입', 2022, 'y', 20000, '아우디', 'AUDI.jpg',0);
insert into rentcar values(null, 'eclass','수입', 2022, 'y', 20000, '벤츠', 'eclass.jpg',0);
insert into rentcar values(null, 'malibu','중소형', 2022, 'y', 20000, '쉐보레', 'malibu.jpg',0);
insert into rentcar values(null, 'ray','경형', 2022, 'y', 20000, '기아', 'ray.jpg',0);
insert into rentcar values(null, 'sm6','중소형', 2022, 'y', 20000, '르노삼성', 'sm6.jpg',0);
insert into rentcar values(null, 'sorento','대형', 2022, 'y', 20000, '르노삼성', 'sorento.jpg',0);
insert into rentcar values(null, 'tivoli','대형', 2022, 'y', 20000, '르노삼성', 'tivoli.jpg',0);






drop table review;
create table review (
review_num int auto_increment primary key, 
c_id varchar(45) not null,
c_name varchar(45),
car_no int(7) not null,
car_name nvarchar(20),
rating int not null,
text nvarchar(200)
);
alter table review auto_increment=1;

insert into review(c_id, c_name, car_no, car_name, rating, text) 
values(1, '김진일', 1, '아반떼', 5, '좋아요');

select * from review;

alter table review
add foreign key (car_no) references rentcar(car_no);


drop table cust_service;
create table cust_service (
customer_no int(5),
board_no int(5),
board_group char(1),
review_name nvarchar(45),
board_contents nvarchar(500),
anwser nvarchar(500)
);

alter table cust_service
add foreign key (customer_no) references customer(customer_no);


drop table coupon_create;
create table coupon_create (
coupon_no int(3),
coupon_name nvarchar(20),
customer_no int(5),
coupon_expiry datetime,
coupon_used char(1)
);

alter table coupon_create
add foreign key (customer_no) references customer(customer_no);
alter table coupon_create
add foreign key (coupon_name) references coupon_info(coupon_name);

drop table coupon_info;
create table coupon_info (
coupon_name nvarchar(20),
coupon_discount int(5),
coupon_discount_per int(5)
);
