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

insert into isa.pharmacies (id, description, name, rating, address_id)
values (1, "Tiny Pill", "Tiny Pill", 5, 1);

insert into isa.persons (id, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (1, "2000-1-1 8:00:00", "Nikola", 0, "Pantelic", "05123123123", 1);
insert into isa.patients (id,person_id) values (1,1);

insert into isa.persons (id, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (2, "1997-1-1 8:00:00", "Nikola", 0, "Gudelj", "05123123123", 2);
insert into isa.employees(id,employee_type,person_id) values (1,1,2);

insert into isa.persons (id, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (3, "1998-1-1 8:00:00", "Sasa", 0, "Strbacki", "05123123123", 3);
insert into isa.employees(id,employee_type,person_id) values (2,1,3);

insert into isa.shifts (id, end, start, pharmacy_id,employee_id)
values (1, "2020-1-1 8:00:00", "2020-1-1 16:00:00", 1,1);
insert into isa.employees_shifts(employee_id,shifts_id) values (1,1);
insert into isa.pharmacies_shifts(pharmacy_id,shifts_id) values (1,1);

insert into isa.shifts (id, end, start, pharmacy_id,employee_id)
values (2, "2021-2-2 8:00:00", "2021-2-2 16:00:00", 1,1);
insert into isa.employees_shifts(employee_id,shifts_id) values (1,2);
insert into isa.pharmacies_shifts(pharmacy_id,shifts_id) values (1,2);

insert into isa.shifts (id, end, start, pharmacy_id,employee_id)
values (3, "2021-2-2 8:00:00", "2021-2-2 16:00:00", 1,2);
insert into isa.employees_shifts(employee_id,shifts_id) values (2,3);
insert into isa.pharmacies_shifts(pharmacy_id,shifts_id) values (1,3);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (1, 2000, 10000, "2020-1-1 8:00:00", 1, 1, 1);
insert into isa.patients_appointment(patient_id,appointment_id) values (1,1);
insert into isa.shifts_appointments(shift_id,appointments_id) values (1,1);


insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (2, 2000, 1800000000000, "2021-2-2 8:00:00", 1, null, 2);
insert into isa.shifts_appointments(shift_id,appointments_id) values (2,2);

insert into isa.appointments (id, amount, duration, start, type, patient_id, shift_id)
values (3, 2000, 1800000000000, "2021-2-2 9:00:00", 1, null, 2);

