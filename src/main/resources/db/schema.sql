CREATE SEQUENCE  if not exists  premieres_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;



CREATE TABLE  if not exists  premieres (
    id int8  NOT null default nextval('premieres_id_seq'::regclass),
    name varchar(64) not NULL,
    description varchar(255) not NULL,
    ageCategory int8 not NULL,
    numberOfSeats int8 not NULL,
    PRIMARY KEY (id)
    );