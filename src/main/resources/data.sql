--drop table address if exists;
--create table address(id int,street varchar(20),city varchar(20),country_code varchar(5));

create table address(id int,full_address varchar(200),lat decimal,lng decimal);
--INSERT INTO Address (id, full_address, lat, lng) VALUES (1,'26022 Meadowlark Bay,San Antonio,TX',null,null);
--insert into address(id,full_address,lat,lng) values(2,'9914 W Military Dr APT 1121,San Antonio,TX',null,null);
insert into address(id,full_address,lat,lng) values(3,'8100 Pinebrook Dr,San Antonio,TX',null,null);
insert into address(id,full_address,lat,lng) values(4,'7800 Fredericksberg Road,San Antonio,TX',null,null);
insert into address(id,full_address,lat,lng) values(5,'9353, Somerset Road,San Antonio,TX',null,null);

