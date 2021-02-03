drop schema isa;
create schema isa;
SET @@session.time_zone='+00:00';
SELECT @@global.time_zone, @@session.time_zone;

insert into isa.countries (id, code, name)
values (1, "RS", "Srbija");
insert into isa.cities (id, name, postal_code, country_id)
values (1, "Novi Sad", "21000", 1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values (1, 1.1, 2.2, "Temerinska 222", 1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(2,4.4,5.5,"Janka Čmelika 55",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(3,2.2,3.3,"Jerneja kopitara 90",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(4,1.1,2.45,"Narodnih heroja 20",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(5,32.22,45.32,"Danila Kisa 13",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(6,21.20,33.22,"Ribarske obale 1",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(7,33.33,22.21,"Brace Ribnikar 24",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(8,33.33,22.21,"Janka Cmelika 10",1);

insert into isa.pharmacies (id, description, name, rating, address_id)
values (1, "Tiny Pill", "Tiny Pill", 5, 1);
insert into isa.pharmacies (id, description, name, rating, address_id)
values (2, "Zegin", "Zegin", 5, 2);


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (1,"0112998091229", "2000-1-1 8:00:00", "Nikola", 0, "Pantelic", "05123123123", 2);
insert into isa.patients (id,person_id) values (1,1);
insert into isa.credentials (email,username,is_activated,password,role,uid,person_id) 
values ("pantelacar@gmail.com","pantelaisus",1,"password","ROLE_PATIENT",'337d2368-8bb0-46a5-a33a-7a0fd98d262f',1);
update isa.persons set credentials_email="pantelacar@gmail.com" where id=1;



insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (2,"021099911229", "1998-2-2 8:00:00", "John", 0, "Doe", "0642025209", 3);
insert into isa.patients (id,person_id) values (2,2);
insert into isa.credentials (email,username,is_activated,password,role,uid,person_id) 
values ("mail@gmail.com","johndoe",1,"password","ROLE_PATIENT",'a4881085-96dc-4124-a3b7-34d48459791f',2);
update isa.persons set credentials_email="mail@gmail.com" where id=2;


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (3,"041199310218", "1998-3-4 8:00:00", "Keith", 0, "Smith", "0631212319", 4);
insert into isa.patients (id,person_id) values (3,3);
insert into isa.credentials (email,username,is_activated,password,role,uid,person_id) 
values ("mail1@gmail.com","keithsmith",1,"password","ROLE_PATIENT",'6f822cc2-a137-4be5-9f34-9d38a252b948',3);
update isa.persons set credentials_email="mail1@gmail.com" where id=3;

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (4,"021099110218", "1992-01-12 8:00:00", "Sage", 0, "Finley", "0632312312", 5);
insert into isa.employees(id,employee_type,person_id) values (1,1,4);

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (5,"031099310218", "1992-09-09 8:00:00", "Mackenzie", 0, "Ashton", "0612935123", 6);
insert into isa.employees(id,employee_type,person_id) values (2,1,5);

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (6,"101099110218", "1991-10-10 8:00:00", "Mickey", 0, "Frankie", "062652035", 7);
insert into isa.employees(id,employee_type,person_id) values (3,0,6);

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (7,"130398311218", "1989-05-13 8:00:00", "Jamey", 0, "Angel", "0663155129", 8);
insert into isa.employees(id,employee_type,person_id) values (4,0,7);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (1, "2020-10-04 8:00:00", "2020-10-04 15:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (2, "2020-10-05 8:00:00", "2020-10-05 15:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (3, "2020-10-06 8:00:00", "2020-10-06 15:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (4, "2020-10-08 8:00:00", "2020-10-08 15:00:00", 1,1);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (1, 2000, 1800000000000, "2020-10-04 9:40:00", 1, 1, 1);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (2, 2000, 1800000000000, "2020-10-04 10:30:00", 1, 2, 1);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (3, 1500, 1800000000000, "2020-10-05 13:30:00", 1, 1, 2);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (4, 1500, 1800000000000, "2020-10-08 13:30:00", 1, 2, 4);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (5, "2020-11-04 9:00:00", "2020-11-04 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (6, "2020-11-05 9:00:00", "2020-11-05 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (7, "2020-11-07 9:00:00", "2020-11-07 17:00:00", 1,2);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (8, "2020-11-10 14:00:00", "2020-11-10 20:00:00", 1,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (9, "2020-11-12 14:00:00", "2020-11-12 20:00:00", 1,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (10, "2020-11-14 14:00:00", "2020-11-14 20:00:00", 1,3);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (5, 500, 1800000000000, "2020-11-10 18:00:00", 0, 1, 8);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (6, 1000, 1800000000000, "2020-11-10 19:00:00", 0, 2, 8);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (7, 3000, 1800000000000, "2020-10-05 13:30:00", 0, 1, 9);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (8, 1500, 1800000000000, "2020-10-05 17:30:00", 0, 2, 9);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (11, "2020-11-18 14:00:00", "2020-11-18 20:00:00", 1,4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (12, "2020-11-19 14:00:00", "2020-11-19 20:00:00", 1,4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (13, "2020-11-21 14:00:00", "2020-11-21 20:00:00", 1,4);


insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (14, "2021-02-15 12:00:00", "2021-02-15 18:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (15, "2021-02-18 12:00:00", "2021-02-18 18:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (16, "2021-02-19 12:00:00", "2021-02-19 18:00:00", 1,1);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (9, 1500, 1800000000000, "2021-02-15 12:00:00", 1, null, 14);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (10, 1500, 1800000000000, "2021-02-15 13:00:00", 1, null, 14);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (11, 1000, 1800000000000, "2021-02-15 14:00:00", 1, null, 14);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (12, 1000, 1800000000000, "2021-02-19 13:00:00", 1, null, 16);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (13, 1000, 1800000000000, "2021-02-19 14:00:00", 1, null, 16);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (14, 1000, 1800000000000, "2020-11-21 15:00:00", 1, null, 16);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (17, "2021-02-23 8:00:00", "2021-02-23 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (18, "2021-02-26 8:00:00", "2021-02-26 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (19, "2021-02-24 8:00:00", "2021-02-24 17:00:00", 1,2);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (20, "2021-02-13 8:00:00", "2021-02-13 8:00:00", 1,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (21, "2021-02-15 8:00:00", "2021-02-15 17:00:00", 1,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (22, "2021-02-16 8:00:00", "2021-02-16 17:00:00", 1,3);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (23, "2021-02-25 13:00:00", "2021-02-13 20:00:00", 1,4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (24, "2021-02-27 13:00:00", "2021-02-27 20:00:00", 1,4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (25, "2021-02-28 13:00:00", "2021-02-28 20:00:00", 1,4);

insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (1, "Ibuprofen", 0, 0, "Bayer", "Brufen", false);
insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (2, "", 0, 0, "Bayer", "Strepsils", false);

insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (1, 200.5, "2022-03-03 00:00:00", 5, 1, 1);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (2, 300.0, "2022-03-03 00:00:00", 20, 2, 1);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (3, 210, "2022-03-03 00:00:00", 30, 1, 2);

update isa.appointments set patient_id=null where id>1;
