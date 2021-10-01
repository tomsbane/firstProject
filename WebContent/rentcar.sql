drop table rentcar;

create table rentcar(
car_no int(7) auto_increment not null primary key,
car_name nvarchar(20),
car_year int,
car_reserve char(1),
car_price int,
car_brand nvarchar(15),
car_image varchar(20),
car_readCount int
);


create sequence rentcar_seq;
insert into rentcar values(rentcar_seq.nextval, 'k3', 2022, 'y', 20000, '기아', 'k3.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'mornig', 2022, 'y', 20000, '기아', 'morning.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'avante', 2022, 'y', 20000, '현대', 'avante.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'A6TDI', 2022, 'y', 20000, '아우디', 'A6TDI.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'eclass', 2022, 'y', 20000, '벤츠', 'eclass.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'malibu', 2022, 'y', 20000, '쉐보레', 'malibu.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'ray', 2022, 'y', 20000, '기아', 'ray.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'sm6', 2022, 'y', 20000, '르노삼성', 'sm6.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'sorento', 2022, 'y', 20000, '르노삼성', 'sorento.jpg' ,0);
insert into rentcar values(rentcar_seq.nextval, 'tivoli', 2022, 'y', 20000, '르노삼성', 'tivoli.jpg' ,0);

alter table rentcar auto_increment=1;

insert into rentcar values(null,'k3', 2022, 'y', 20000, '기아', 'k3.jpg', 0);
insert into rentcar values(null, 'mornig', 2022, 'y', 20000, '기아', 'morning.jpg', 0);
insert into rentcar values(null, 'avante', 2022, 'y', 20000, '현대', 'avante.jpg',0);
insert into rentcar values(null, 'A6TDI', 2022, 'y', 20000, '아우디', 'A6TDI.jpg',0);
insert into rentcar values(null, 'eclass', 2022, 'y', 20000, '벤츠', 'eclass.jpg',0);
insert into rentcar values(null, 'malibu', 2022, 'y', 20000, '쉐보레', 'malibu.jpg',0);
insert into rentcar values(null, 'ray', 2022, 'y', 20000, '기아', 'ray.jpg',0);
insert into rentcar values(null, 'sm6', 2022, 'y', 20000, '르노삼성', 'sm6.jpg',0);
insert into rentcar values(null, 'sorento', 2022, 'y', 20000, '르노삼성', 'sorento.jpg',0);
insert into rentcar values(null, 'tivoli', 2022, 'y', 20000, '르노삼성', 'tivoli.jpg',0);

select * from rentcar;