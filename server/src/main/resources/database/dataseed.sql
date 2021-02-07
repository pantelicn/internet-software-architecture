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
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(9,33.33,22.21,"Narodnih Heroja 89",1);
insert into isa.addresses(id, latitude, longitude, street_name, city_id)
values(10,33.33,22.21,"Janka Cmelika 13",1);

insert into isa.pharmacies (id, description, name, rating, address_id)
values (1, "Tiny Pill", "Tiny Pill", 5, 1);
insert into isa.pharmacies (id, description, name, rating, address_id)
values (2, "Zegin", "Zegin", 5, 2);


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (1,"0112998091229", "2000-1-1 8:00:00", "Nikola", 0, "Pantelic", "05123123123", 2);
insert into isa.patients (id,person_id,penalties) values (1,1,0);
insert into isa.credentials (email,username,is_activated,has_logged_in_before,password,role,uid,person_id) 
values ("pantelacar@gmail.com","pantelaisus",1,1,"password","ROLE_PATIENT",'337d2368-8bb0-46a5-a33a-7a0fd98d262f',1);
update isa.persons set credentials_email="pantelacar@gmail.com" where id=1;


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (2,"021099911229", "1998-2-2 8:00:00", "John", 0, "Doe", "0642025209", 3);
insert into isa.patients (id,person_id,penalties) values (2,2,0);
insert into isa.credentials (email,username,is_activated,has_logged_in_before,password,role,uid,person_id) 
values ("mail@gmail.com","johndoe",1,1,"password","ROLE_PATIENT",'a4881085-96dc-4124-a3b7-34d48459791f',2);
update isa.persons set credentials_email="mail@gmail.com" where id=2;


insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (3,"041199310218", "1998-3-4 8:00:00", "Keith", 0, "Smith", "0631212319", 4);
insert into isa.patients (id,person_id,penalties) values (3,3,0);
insert into isa.credentials (email,username,is_activated,has_logged_in_before,password,role,uid,person_id) 
values ("mail1@gmail.com","keithsmith",1,1,"password","ROLE_PATIENT",'6f822cc2-a137-4be5-9f34-9d38a252b948',3);
update isa.persons set credentials_email="mail1@gmail.com" where id=3;

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (4,"021099110218", "1992-01-12 8:00:00", "Sage", 0, "Finley", "0632312312", 5);
insert into isa.credentials (email,username,is_activated,has_logged_in_before,password,role,uid,person_id) 
values ("sagefinley@gmail.com","sagefinley",1,0,"password","ROLE_DERMATOLOGIST",'6f922cc2-a137-4be5-9f34-9d38a252b948',4);
update isa.persons set credentials_email="sagefinley@gmail.com" where id=4;
insert into isa.employees(id,employee_type,person_id) values (1,1,4);

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (5,"031099310218", "1992-09-09 8:00:00", "Mackenzie", 0, "Ashton", "0612935123", 6);
insert into isa.credentials (email,username,is_activated,has_logged_in_before,password,role,uid,person_id) 
values ("mickeyashton@gmail.com","mickeyashton",1,1,"password","ROLE_PHARMACIST",'6f192cc2-a137-4be5-9f34-9d38a252b948',5);
update isa.persons set credentials_email="mickeyashton@gmail.com" where id=5;
insert into isa.employees(id,employee_type,person_id) values (2,1,5);

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (6,"101099110218", "1991-10-10 8:00:00", "Mickey", 0, "Frankie", "062652035", 7);
insert into isa.employees(id,employee_type,person_id) values (3,0,6);

insert into isa.persons (id, pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (7,"101099510218", "1995-10-10 8:00:00", "Luis", 0, "Wallace", "063125035", 8);
insert into isa.employees(id,employee_type,person_id) values (4,0,7);

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (8,"130398311218", "1989-05-13 8:00:00", "John", 0, "Scott", "0663155129", 9);
insert into isa.credentials (email,username,is_activated,has_logged_in_before,password,role,uid,person_id) 
values ("kennethlaine1@gmail.com","johnscott",1,1,"password","ROLE_PH_ADMIN",'6f822ca1-a137-4be5-9f34-9d38a252b948',8);
update isa.persons set credentials_email="kennethlaine1@gmail.com" where id=8;
insert into isa.admins (id,admin_type,person_id) values (1,1,8);
update isa.pharmacies set admin_id = 1 where id=1;

insert into isa.persons (id,pid, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (9,"130428311218", "1989-05-13 8:00:00", "Andrew", 0, "Miller", "0613155129", 10);
insert into isa.credentials (email,username,is_activated,has_logged_in_before,password,role,uid,person_id) 
values ("andrewmiller@gmail.com","andrewmiller",1,1,"password","ROLE_PH_ADMIN",'6f822cc1-a125-4be5-9f34-9d38a252b948',9);
update isa.persons set credentials_email="andrewmiller@gmail.com" where id=9;
insert into isa.admins (id,admin_type,person_id) values (2,1,9);
update isa.pharmacies set admin_id=2 where id=2;

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

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (15, 1300, 1800000000000, "2021-02-18 15:00:00", 1, 1, 15);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (16, 1300, 1800000000000, "2021-02-18 16:00:00", 1, 1, 15);


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

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (17, 0, 1800000000000, "2021-02-13 10:00:00", 1, 1, 20);
insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (18, 0, 1800000000000, "2021-02-13 12:00:00", 1, 2, 20);

insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (23, "2021-02-25 13:00:00", "2021-02-13 20:00:00", 1,4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (24, "2021-02-27 13:00:00", "2021-02-27 20:00:00", 1,4);
insert into isa.shifts (id, start, end, pharmacy_id,employee_id)
values (25, "2021-02-28 13:00:00", "2021-02-28 20:00:00", 1,4);

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


insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (1, 200.5, "2022-03-03 00:00:00", 10, 1, 1);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (2, 300.0, "2022-03-03 00:00:00", 20, 2, 1);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (3, 100.0, "2022-03-03 00:00:00", 20, 3, 1);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (4, 150.0, "2022-03-03 00:00:00", 25, 4, 1);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (5, 220.0, "2022-03-03 00:00:00", 15, 5, 1);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (6, 150.0, "2022-03-03 00:00:00", 0, 6, 1);
#insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
#values (7, 150.0, "2022-03-03 00:00:00", 25, 7, 1);

insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (8, 200.5, "2022-03-03 00:00:00", 5, 1, 2);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (9, 300.0, "2022-03-03 00:00:00", 10, 2, 2);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (10, 100.0, "2022-03-03 00:00:00", 15, 3, 2);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (11, 150.0, "2022-03-03 00:00:00", 10, 4, 2);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (12, 220.0, "2022-03-03 00:00:00", 5, 5, 2);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (13, 150.0, "2022-03-03 00:00:00", 10, 6, 2);
insert into isa.stored_drugs (id, amount, valid_until, quantity, drug_id, pharmacy_id)
values (14, 150.0, "2022-03-03 00:00:00", 25, 7, 2);


select * from isa.appointments;

