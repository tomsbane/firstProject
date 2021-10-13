drop table customer;
create table customer (
customer_no int(5) not null primary key,
customer_name nvarchar (20) not null,
customer_pw nvarchar (20) not null,
gender char(1) not null,
birth datetime not null,
tel int (9) not null,
address nvarchar(200),
grade char(1),
bonuspoint int(10),
joindate datetime not null
);

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
update customer set c_grade='admin' where c_id='admin';

create table address(
addr_index int auto_increment primary key,/*auto_increment하려면 primary key*/
c_id varchar(45) not null,
postcode int not null,/*우편번호*/
address1 Nvarchar(60) not null,
address2 Nvarchar(60) not null
);

select * from rentcar;

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
insert into rentcar values(null, 'A6TDI','수입', 2022, 'y', 20000, '아우디', 'A6TDI.jpg',0);
insert into rentcar values(null, 'eclass','수입', 2022, 'y', 20000, '벤츠', 'eclass.jpg',0);
insert into rentcar values(null, 'malibu','중소형', 2022, 'y', 20000, '쉐보레', 'malibu.jpg',0);
insert into rentcar values(null, 'ray','경형', 2022, 'y', 20000, '기아', 'ray.jpg',0);
insert into rentcar values(null, 'sm6','중소형', 2022, 'y', 20000, '르노삼성', 'sm6.jpg',0);
insert into rentcar values(null, 'sorento','SUV', 2022, 'y', 20000, '르노삼성', 'sorento.jpg',0);
insert into rentcar values(null, 'tivoli','SUV', 2022, 'y', 20000, '르노삼성', 'tivoli.jpg',0);

create table order_car(
order_no int not null primary key,
customer_no int not null,
car_no int primary key,
rental_date date not null,
return_date date not null,
rental_price int not null,
rental_status char(1) not null
);





drop table review;
create table review (
review_no int(10),
car_no int(7),
review_name nvarchar(45),
review_contents nvarchar(150),
review_level char(1)
);

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