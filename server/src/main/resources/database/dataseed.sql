insert into isa.country (id, code, name)
values (1, "RS", "Srbija");
insert into isa.city (id, name, postal_code, country_id)
values (1, "Novi Sad", "21000", 1);
insert into isa.address(id, latitude, longitude, street_name, city_id)
values (1, 1.1, 2.2, "Temerinska 222", 1);

insert into isa.person (id, date_of_birth, first_name, gender, last_name, phone_number, address_id)
values (1, "2000-1-1 8:00:00", "Nikola", 0, "Pantelic", "05123123123", 1);

insert into isa.pharmacy (id, description, name, rating, address_id)
values (1, "Tiny Pill", "Tiny Pill", 5, 1);

insert into isa.shift (id, end, start, pharmacy_id)
values (1, "2020-1-1 8:00:00", "2020-1-1 16:00:00", 1);

insert into isa.appointment (id, amount, duration, start, type, patient_id, shift_id)
values (1, 20, 1000, "2020-1-1 8:00:00", 1, 1, 1);
insert into isa.appointment (id, amount, duration, start, type, patient_id, shift_id)
values (2, 20, 1000, "2020-1-1 8:00:00", 1, 1, 1);
