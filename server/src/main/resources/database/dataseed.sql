# drop schema isa;
# create schema isa; # Used to create initial schema
# SET @@session.time_zone='+00:00';
# SELECT @@global.time_zone, @@session.time_zone;

# before inserting, run spring-boot application first in order to create tables.

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
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(9,33.33,22.21,"Narodnih Heroja 89",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(11,33.33,22.21,"Jukijana Mušickog 23",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(12,33.33,22.21,"Kralja Milana 39",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(13,33.33,22.21,"Bulevar Oslobođenja 2",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(14,33.33,22.21,"Nikole Pašića 3",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(15,33.33,22.21,"Vojvode Putnika 73",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(10,33.33,22.21,"Radnička 12",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(16,33.33,22.21,"Topolska 18",1);


insert into isa.pharmacies (id, amount, description, name, address_id)
values (1, 250, "Tiny Pill", "Tiny Pill", 1);
insert into isa.pharmacies (id, amount, description, name, address_id)
values (2, 200, "Zegin", "Zegin", 2);


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (1,"0112998091229", "2000-1-1", "Sam", 0, "Hunter", "05123123123", 3);
insert into isa.patients (id,person_id,penalties) values (1,1,3);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("samhunter@gmail.com","samhunter",1,1,"password","ROLE_PATIENT",'337d2368-8bb0-46a5-a33a-7a0fd98d262f',1);
update isa.persons set credentials_email="samhunter@gmail.com" where id=1;

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (2,"021099911229", "1998-2-2", "John", 0, "Doe", "0642025209", 4);
insert into isa.patients (id,person_id,penalties) values (2,2,0);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("johndoe@gmail.com","johndoe",1,1,"password","ROLE_PATIENT",'a4881085-96dc-4124-a3b7-34d48459791f',2);
update isa.persons set credentials_email="johndoe@gmail.com" where id=2;

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (3,"041199310218", "1998-3-4", "Keith", 0, "Smith", "0631212319", 5);
insert into isa.patients (id,person_id,penalties) values (3,3,0);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("keithsmith@gmail.com","keithsmith",1,1,"password","ROLE_PATIENT",'6f822cc2-a137-4be5-9f34-9d38a252b948',3);
update isa.persons set credentials_email="keithsmith@gmail.com" where id=3;

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (4,"0210991102181", "1992-01-12", "Sage", 0, "Finley", "0632312312", 6);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id)
values ("sagefinley@gmail.com","sagefinley",1,1,"password","ROLE_DERMATOLOGIST",'6f922cc2-a137-4be5-9f34-9d38a252b948',4);
update isa.persons set credentials_email="sagefinley@gmail.com" where id=4;
insert into isa.employees(id,employee_type,person_id) values (1,1,4);


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (5,"031099310218", "1992-09-09", "Mackenzie", 0, "Ashton", "0612935123", 7);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("mackenzieashton@gmail.com","mackenzieashton",1,0,"password","ROLE_DERMATOLOGIST",'6f192cc2-a137-4be5-9f34-9d38a252b948',5);
update isa.persons set credentials_email="mackenzieashton@gmail.com" where id=5;
insert into isa.employees(id,employee_type,person_id) values (2,1,5);


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (6,"1010991102181", "1991-10-10", "Mickey", 0, "Frankie", "062652035", 8);
insert into isa.employees(id,employee_type, person_id) values (3, 0, 6);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("mickeyfrankie@gmail.com","mickeyfrankie",1,1,"password","ROLE_PHARMACIST",'6f191cc2-a137-4be5-9f34-9d38a252b948',6);
update isa.persons set credentials_email="mickeyfrankie@gmail.com" where id=6;


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (7,"1010995102181", "1995-10-10", "Luis", 0, "Wallace", "063125035", 9);
insert into isa.employees(id,employee_type, person_id) values (4, 0, 7);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("luiswallace@gmail.com","luiswallace",1,0,"password","ROLE_PHARMACIST",'6f341cc2-a137-4be5-9f34-9d38a252b948',7);
update isa.persons set credentials_email="luiswallace@gmail.com" where id=7;

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (8,"130398311218", "1989-05-13", "John", 0, "Scott", "0663155129", 10);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("johnscott@gmail.com","johnscott",1,1,"password","ROLE_PH_ADMIN",'6f822ca1-a137-4be5-9f34-9d38a252b948',8);
update isa.persons set credentials_email="johnscott@gmail.com" where id=8;
insert into isa.admins (id,admin_type,person_id) values (1,1,8);
update isa.pharmacies set admin_id = 1 where id=1;

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (9,"130428311218", "1989-05-13", "Andrew", 0, "Miller", "0613155129", 11);
insert into isa.credentials (email,username,is_activated,has_changed_initial_password,password,role,uid,person_id) 
values ("andrewmiller@gmail.com","andrewmiller",1,1,"password","ROLE_PH_ADMIN",'6f822cc1-a125-4be5-9f34-9d38a252b948',9);
update isa.persons set credentials_email="andrewmiller@gmail.com" where id=9;
insert into isa.admins (id,admin_type,person_id) values (2,1,9);
update isa.pharmacies set admin_id=2 where id=2;

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (1, "2021-01-10 8:00:00", "2021-01-10 15:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (2, "2021-01-11 8:00:00", "2021-01-11 15:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (3, "2021-01-12 8:00:00", "2021-01-12 15:00:00", 2,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (4, "2021-01-13 8:00:00", "2021-01-13 15:00:00", 2,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (5, "2021-01-14 8:00:00", "2021-01-14 15:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (6, "2021-01-15 8:00:00", "2021-01-15 15:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (7, "2021-01-16 8:00:00", "2021-01-16 15:00:00", 2,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (8, "2021-01-17 8:00:00", "2021-01-17 15:00:00", 2,1);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (1, 1300, 1800000000000, "2021-01-10 9:40:00", 1, 1, 1);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (2, 1300, 1800000000000, "2021-01-10 10:30:00", 1, 2, 1);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (3, 750, 1800000000000, "2021-01-10 11:40:00", 1, 3, 1);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (4, 1200, 1800000000000, "2021-01-11 9:40:00", 1, 1, 2);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (5, 1100, 1800000000000, "2021-01-11 10:30:00", 1, 2, 2);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (6, 990, 1800000000000, "2021-01-11 11:40:00", 1, 3, 2);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (7, 1660, 1800000000000, "2021-01-12 9:40:00", 1, 1, 3);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (8, 2200, 1800000000000, "2021-01-12 10:30:00", 1, 2, 3);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (9, 1980, 1800000000000, "2021-01-12 11:40:00", 1, 3, 3);


insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (9, "2021-02-14 9:00:00", "2021-02-14 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (10, "2021-02-15 9:00:00", "2021-02-15 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (11, "2021-02-16 9:00:00", "2021-02-16 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (12, "2021-02-17 9:00:00", "2021-02-17 17:00:00", 1,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (13, "2021-02-18 9:00:00", "2021-02-18 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (14, "2021-02-19 9:00:00", "2021-02-19 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (15, "2021-02-20 9:00:00", "2021-02-20 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (17, "2021-02-21 9:00:00", "2021-02-21 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (18, "2021-02-22 9:00:00", "2021-02-22 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (19, "2021-02-23 9:00:00", "2021-02-23 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (20, "2021-02-24 9:00:00", "2021-02-24 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (21, "2021-02-25 9:00:00", "2021-02-25 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (22, "2021-02-26 9:00:00", "2021-02-26 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (23, "2021-02-27 9:00:00", "2021-02-27 17:00:00", 2,2);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (24, "2021-02-28 9:00:00", "2021-02-28 17:00:00", 2,2);


insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (25, "2021-02-01 14:00:00", "2021-02-01 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (26, "2021-02-02 14:00:00", "2021-02-02 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (27, "2021-02-03 14:00:00", "2021-02-03 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (28, "2021-02-04 14:00:00", "2021-02-04 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (29, "2021-02-05 14:00:00", "2021-02-05 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (30, "2021-02-06 14:00:00", "2021-02-06 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (31, "2021-02-07 14:00:00", "2021-02-07 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (32, "2021-02-08 14:00:00", "2021-02-08 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (33, "2021-02-09 14:00:00", "2021-02-09 20:00:00", 2,3);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (10, 0, 1800000000000, "2021-02-01 14:30:00", 0, 1, 25);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (11, 0, 1800000000000, "2021-02-01 15:30:00", 0, 2, 25);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (12, 0, 1800000000000, "2021-02-01 17:30:00", 0, 3, 25);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (13, 0, 1800000000000, "2021-02-02 14:30:00", 0, 1, 26);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (14, 0, 1800000000000, "2021-02-02 15:30:00", 0, 2, 26);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (15, 0, 1800000000000, "2021-02-02 17:30:00", 0, 3, 26);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (16, 0, 1800000000000, "2021-02-03 14:30:00", 0, 1, 27);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (17, 0, 1800000000000, "2021-02-03 15:30:00", 0, 2, 27);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (18, 0, 1800000000000, "2021-02-03 17:30:00", 0, 3, 27);



insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (34, "2021-02-22 14:00:00", "2021-02-22 20:00:00", 1, 4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (35, "2021-02-25 14:00:00", "2021-02-25 20:00:00", 1, 4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (36, "2021-02-28 14:00:00", "2021-02-28 20:00:00", 1, 4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (37, "2021-03-02 14:00:00", "2021-03-02 20:00:00", 1, 4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (38, "2021-03-05 14:00:00", "2021-03-05 20:00:00", 1, 4);


insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (39, "2021-02-20 12:00:00", "2021-02-20 18:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (40, "2021-02-22 12:00:00", "2021-02-22 18:00:00", 2,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (41, "2021-02-24 12:00:00", "2021-02-24 18:00:00", 2,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (42, "2021-02-28 12:00:00", "2021-02-28 18:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (43, "2021-03-02 12:00:00", "2021-03-02 18:00:00", 1,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (44, "2021-03-04 12:00:00", "2021-03-04 18:00:00", 2,1);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (45, "2021-03-06 12:00:00", "2021-03-06 18:00:00", 2,1);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (19, 1500, 1800000000000, "2021-02-20 13:00:00", 1, null, 39);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (20, 1320, 1800000000000, "2021-02-20 15:00:00", 1, null, 39);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (21, 2200, 1800000000000, "2021-02-20 16:00:00", 1, null, 39);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (22, 2340, 1800000000000, "2021-02-20 17:00:00", 1, null, 39);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (23, 1500, 1800000000000, "2021-02-22 13:00:00", 1, null, 40);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (24, 1320, 1800000000000, "2021-02-22 15:00:00", 1, null, 40);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (25, 2200, 1800000000000, "2021-02-22 16:00:00", 1, null, 40);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (26, 2340, 1800000000000, "2021-02-22 17:00:00", 1, null, 40);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (27, 1500, 1800000000000, "2021-02-28 13:00:00", 1, null, 42);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (28, 1320, 1800000000000, "2021-02-28 15:00:00", 1, null, 42);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (29, 2200, 1800000000000, "2021-02-28 16:00:00", 1, null, 42);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (30, 2340, 1800000000000, "2021-02-28 17:00:00", 1, null, 42);


insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (31, 2200, 1800000000000, "2021-02-24 12:30:00", 1, 1, 41);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (32, 1250, 1800000000000, "2021-02-24 14:30:00", 1, 2, 41);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (33, 2150, 1800000000000, "2021-02-24 16:30:00", 1, 3, 41);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (34, 2200, 1800000000000, "2021-03-02 12:30:00", 1, 1, 43);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (35, 1250, 1800000000000, "2021-03-02 14:30:00", 1, 2, 43);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (36, 2150, 1800000000000, "2021-03-02 16:30:00", 1, 3, 43);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (37, 2200, 1800000000000, "2021-03-04 12:30:00", 1, 1, 44);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (38, 1250, 1800000000000, "2021-03-04 14:30:00", 1, 2, 44);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (39, 2150, 1800000000000, "2021-03-04 16:30:00", 1, 3, 44);


insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (46, "2021-02-18 14:00:00", "2021-02-01 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (47, "2021-02-19 14:00:00", "2021-02-02 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (48, "2021-02-20 14:00:00", "2021-02-20 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (49, "2021-02-24 14:00:00", "2021-02-24 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (50, "2021-02-26 14:00:00", "2021-02-26 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (51, "2021-02-28 14:00:00", "2021-02-28 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (52, "2021-03-02 14:00:00", "2021-03-02 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (53, "2021-03-04 14:00:00", "2021-03-04 20:00:00", 2,3);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (54, "2021-03-06 14:00:00", "2021-03-06 20:00:00", 2,3);



insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (1, "Gel namenjen za masnu kožu", 4, 5, "MiksaMed", "Dermoidealia gel", false);
insert into isa.drug_ingredients (drug_id,ingredients) values (1,"Glycerin");
insert into isa.drug_ingredients (drug_id,ingredients) values (1,"D-panthenol");
insert into isa.drug_ingredients (drug_id,ingredients) values (1,"Allantoin");

insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (2, "Gel namenjen za masnu kožu", 4, 5, "ESI", "Acknes gel", false);
insert into isa.drug_ingredients (drug_id,ingredients) values (2,"Glycerin");
insert into isa.drug_ingredients (drug_id,ingredients) values (2,"D-panthenol");
insert into isa.drug_ingredients (drug_id,ingredients) values (2,"Allantoin");

insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (3, "Gel namenjen za masnu kožu", 4, 5, "Avene", "Cleanance Comedomed", false);
insert into isa.drug_ingredients (drug_id,ingredients) values (3,"Glycerin");
insert into isa.drug_ingredients (drug_id,ingredients) values (3,"D-panthenol");
insert into isa.drug_ingredients (drug_id,ingredients) values (3,"Allantoin");

insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (4, "Gel za upaljenu i iritiranu kožu", 4, 5, "Hiper", "Hama gel", false);
insert into isa.drug_ingredients (drug_id,ingredients) values (4,"Hamamelis destilat");
insert into isa.drug_ingredients (drug_id,ingredients) values (4,"D-panthenol");
insert into isa.drug_ingredients (drug_id,ingredients) values (4,"Extr. kamilice");

insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (5, "Krema za upaljenu i iritiranu kožu", 4, 4, "A-Derma", "Epitheliale AH Ultra", false);
insert into isa.drug_ingredients (drug_id,ingredients) values (5,"Glycerin");
insert into isa.drug_ingredients (drug_id,ingredients) values (5,"Stearic acid");
insert into isa.drug_ingredients (drug_id,ingredients) values (5,"Batyl alcohol");

insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (6, "Antidepresiv", 0, 0, "Hemofarm", "Flunisan", true);
insert into isa.drug_ingredients (drug_id,ingredients) values (6,"Fluoksetin");

insert into isa.drugs (id, additional_notes, drug_type, intake_type, manufacturer, name, requires_prescription)
values (7, "Antidepresiv", 0, 0, "Bosnalijek", "Flusetin", true);
insert into isa.drug_ingredients (drug_id,ingredients) values (7,"Fluoksetin");

insert into isa.drugs_alternatives (drug_id,alternatives_id) values (1,2);
insert into isa.drugs_alternatives (drug_id,alternatives_id) values (1,3);
insert into isa.drugs_alternatives (drug_id,alternatives_id) values (2,1);
insert into isa.drugs_alternatives (drug_id,alternatives_id) values (2,3);
insert into isa.drugs_alternatives (drug_id,alternatives_id) values (3,1);
insert into isa.drugs_alternatives (drug_id,alternatives_id) values (3,2);
insert into isa.drugs_alternatives (drug_id,alternatives_id) values (6,7);
insert into isa.drugs_alternatives (drug_id,alternatives_id) values (7,6);

insert into isa.patients_allergic_to (patient_id,allergic_to_id) values (1,1);
insert into isa.patients_allergic_to (patient_id,allergic_to_id) values (2,2);
insert into isa.patients_allergic_to (patient_id,allergic_to_id) values (3,3);


insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (1, 200.5, "2022-03-03 00:00:00", 1, 1, 1, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (2, 300.0, "2022-03-03 00:00:00", 20, 2, 1, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (3, 100.0, "2022-03-03 00:00:00", 20, 3, 1, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (4, 150.0, "2022-03-03 00:00:00", 25, 4, 1, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (5, 220.0, "2022-03-03 00:00:00", 15, 5, 1, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (6, 150.0, "2022-03-03 00:00:00", 0, 6, 1, 0);
#insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
#values (7, 150.0, "2022-03-03 00:00:00", 25, 7, 1);

insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (8, 200.5, "2022-03-03 00:00:00", 5, 1, 2, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (9, 300.0, "2022-03-03 00:00:00", 10, 2, 2, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id,version)
values (10, 100.0, "2022-03-03 00:00:00", 15, 3, 2, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id, version)
values (11, 150.0, "2022-03-03 00:00:00", 10, 4, 2, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id,version)
values (12, 220.0, "2022-03-03 00:00:00", 5, 5, 2, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id,version)
values (13, 150.0, "2022-03-03 00:00:00", 10, 6, 2, 0);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id,version)
values (14, 150.0, "2022-03-03 00:00:00", 25, 7, 2, 0);

insert into isa.appointment_reports (id, appointment_info, appointment_id)
values (1, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ac sem erat. Sed dui neque, aliquam rhoncus fermentum vitae, facilisis quis metus.", 1);
insert into isa.appointment_reports (id, appointment_info, appointment_id)
values (2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ac sem erat. Sed dui neque, aliquam rhoncus fermentum vitae, facilisis quis metus.", 2);
insert into isa.appointment_reports (id, appointment_info, appointment_id)
values (3, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ac sem erat. Sed dui neque, aliquam rhoncus fermentum vitae, facilisis quis metus.", 10);

insert into isa.drug_reservations (id, is_dispensed, pick_up_before, quantity, patient_id, stored_drug_id)
values (1, true, "2021-1-5", 2, 1, 1);
insert into isa.drug_reservations (id, is_dispensed, pick_up_before, quantity, patient_id, stored_drug_id)
values (2, true, "2021-1-6", 3, 2, 1);
insert into isa.drug_reservations (id, is_dispensed, pick_up_before, quantity, patient_id, stored_drug_id)
values (3, true, "2021-1-6", 3, 2, 2);

-- asd
insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
	values (80,"123123324", "2000-1-1", "Sys", 0, "Admin1", "05123123123", 3);
insert into isa.credentials(email, has_changed_initial_password, is_activated, password, role, uid, username, person_id)
	values("sysadmin1@mail.com", 0, 1, "password", "ROLE_SYS_ADMIN", "6f822cc2-a137-4be5-9f34-9d38a252b9ff", "sysadmin1", 80);
update isa.persons set credentials_email="sysadmin1@mail.com" where id=80;

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
	values (81,"153123324", "1980-1-1", "Supplier", 0, "Supplierovsky", "05123123123", 3);
insert into isa.credentials(email, has_changed_initial_password, is_activated, password, role, uid, username, person_id)
	values("suppliersupplierovsky@mail.com", 0, 1, "password", "ROLE_SUPPLIER", "6f822ac3-b137-4be5-9f34-9d38a252b9ff", "suppliersupplierovsky", 81);
update isa.persons set credentials_email="suppliersupplierovsky@mail.com" where id=81;
insert into isa.supplier(person_id) values(81);

insert into isa.purchase_order(id, offer_deadline, status) values(1, "2022-01-01", 0);
insert into isa.purchase_order(id, offer_deadline, status) values(2, "2021-05-01", 0);
insert into isa.purchase_order(id, offer_deadline, status) values(3, "2021-02-15", 0);
insert into isa.purchase_order(id, offer_deadline, status) values(4, "2020-12-01", 0);
insert into isa.purchase_order(id, offer_deadline, status) values(5, "2021-3-15", 0);

insert into isa.ordered_drug(id, amount, drug_id, order_id) values(11, 100, 1, 1);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(2, 1200, 4, 1);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(3, 2500, 5, 1);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(4, 3300, 3, 1);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(5, 20000, 2, 1);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(6, 2300, 7, 2);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(7, 10230, 6, 2);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(8, 1010, 5, 3);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(9, 300, 2, 3);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(10, 4400, 1, 4);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(12, 5000, 4, 5);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(13, 100, 2, 5);
insert into isa.ordered_drug(id, amount, drug_id, order_id) values(14, 25000, 6, 5);


insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(1, 11);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(1, 2);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(1, 3);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(1,4);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(1,5);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(2, 6);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(2, 7);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(3, 8);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(3, 9);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(4, 10);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(5, 12);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(5, 13);
insert into isa.purchase_order_ordered_drugs(purchase_order_id, ordered_drugs_id) values(5, 14);

insert into isa.supplier_drug_stock(id, amount, drug_id, supplier_person_id) values(1, 10000, 1, 81);
insert into isa.supplier_drug_stock(id, amount, drug_id, supplier_person_id) values(2, 2000, 2, 81);
insert into isa.supplier_drug_stock(id, amount, drug_id, supplier_person_id) values(3, 5000, 3, 81);
insert into isa.supplier_drug_stock(id, amount, drug_id, supplier_person_id) values(4, 10000, 4, 81);
insert into isa.supplier_drug_stock(id, amount, drug_id, supplier_person_id) values(5, 10999, 6, 81);
insert into isa.supplier_drug_stock(id, amount, drug_id, supplier_person_id) values(6, 2000, 7, 81);

select * from isa.appointments;
insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
	values (82,"1532123324", "1980-1-1", "Pacijent", 0, "Pacijentovski", "2130320", 3);
insert into isa.credentials(email, has_changed_initial_password, is_activated, password, role, uid, username, person_id)
	values("patient@mail.com", 0, 1, "password", "ROLE_PATIENT", "6f822ac3-b257-4be5-9f34-9d38a252b9ff", "patient", 82);
update isa.persons set credentials_email="patient@mail.com" where id=82;
insert into isa.patients(id, penalties, person_id) values(4, 0, 82);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
	values (80, 1300, 1800000000000, "2021-01-10 9:40:00", 1, 4, 1);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
	values (81, 200, 1800000000000, "2021-01-10 9:40:00", 1, 4, 2);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
	values (82, 13500, 1800000000000, "2021-01-10 9:40:00", 1, 4, 3);

insert into isa.appointment_reports(id, appointment_info, appointment_id) values(4, "Obican pregled za pacijenta.", 80);
insert into isa.appointment_reports(id, appointment_info, appointment_id) values(5, "Operacija jezicne sluzokoze", 81);
insert into isa.appointment_reports(id, appointment_info, appointment_id) values(6, "Postop. kontrola.", 82);
