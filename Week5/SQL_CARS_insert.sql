CREATE DATABASE CARS;
CREATE TABLE CARS.PUBLIC.ENGINE(
id integer PRIMARY KEY,
displacement integer,
power integer
);
CREATE TABLE CARS.PUBLIC.CAR (
id integer PRIMARY KEY,
model varchar(20),
make date,
id_engine integer REFERENCES engine (id),
price integer
);

create sequence car_id_seq;
alter table cars.public.car alter id set default nextval('car_id_seq');

create sequence engine_id_seq;
alter table cars.public.engine alter id set default nextval('engine_id_seq');


INSERT INTO CARS.PUBLIC.ENGINE (displacement,power) values(2,203);
INSERT INTO CARS.PUBLIC.ENGINE (displacement,power) values(2,243);
INSERT INTO CARS.PUBLIC.ENGINE (displacement,power) values(2,255);
INSERT INTO CARS.PUBLIC.ENGINE (displacement,power) values(3,365);
INSERT INTO CARS.PUBLIC.ENGINE (displacement,power) values(3,325);
INSERT INTO CARS.PUBLIC.ENGINE (displacement,power) values(3,315);

INSERT INTO CARS.PUBLIC.CAR (model,make,id_engine,price) values ('Audi A3 1.6','2015-01-05',1,900000);
INSERT INTO CARS.PUBLIC.CAR (model,make,id_engine,price) values ('Volksagen Caddy life','2013-05-01',2,854000);
INSERT INTO CARS.PUBLIC.CAR (model,make,id_engine,price) values ('Skoda Fabia 1.6','2014-09-08',3,564000);
INSERT INTO CARS.PUBLIC.CAR (model,make,id_engine,price) values ('Mazda 2 1.5','2015-05-12',4,789000);
INSERT INTO CARS.PUBLIC.CAR (model,make,id_engine,price) values ('Opel Antara 2.4 AT','2017-01-05',5,256000);
INSERT INTO CARS.PUBLIC.CAR (model,make,id_engine,price) values ('Lexus IS 250','2013-04-07',2,256000);
