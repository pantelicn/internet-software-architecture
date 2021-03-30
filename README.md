# internet-software-architecture
University project for Internet Software Architecture [2020]

[![Build Status](https://travis-ci.com/nikolapantelic-ftn/internet-software-architecture.svg?branch=main)](https://travis-ci.com/nikolapantelic-ftn/internet-software-architecture)

#### ~~Produkciona verzija dostupna na [isa-pharmacy.herokuapp.com](https://isa-pharmacy.herokuapp.com)~~
## Pokretanje Vue aplikacije
Za pokretanje frontend dela aplikacije pogledati client/README.md.

## Pokretanje API servera
Server koristi Spring framework koji je inicijalizovan pomocu [Spring Initializr](https://start.spring.io/) alata.

Korišćeni sistem za upravljanje bazama podataka je [MySql](https://www.mysql.com/).

#### Pre pokretanja potrebno je podesiti sledeće promenljive okruženja:
- MYSQL_HOST - opcioni hostname za pristupanje bazi podataka. Podrazumevana vrednost je `locahost:3306`
- MYSQL_USERNAME - korisničko ime za pristupanje bazi podataka.
- MYSQL_PASSWORD - lozinka za pristupanje bazi podataka.
- MAIL_USERNAME - e-mail sa kojeg će se slati pošta od strane sistema
- MAIL_PASSWORD - lozinka pomenutog e-mail naloga

takodje je potrebno obezbediti postojanje šeme sa nazivom "isa" u MySql bazi

#### Za pokretanje koristiti maven:
postaviti se u server direktorijum i izvšiti:

`$ mvn spring-boot:run`

Nakon prvog pokretanja projekta tabele u bazi će biti kreirane i može se iskoristiti sql skripta u folderu `server/src/main/resources/database` za popunjavanje podacima

### Uloge
- Student 1: Nikola Pantelić RA-234/2017
- Student 2: Darjan Rac RA-144/2017 (**Nije radio**)
- Student 3: Saša Štrbački RA-166/2017
- Student 4: Nikola Gudelj RA-21/2017

### Napomene
#### Student 1
- Za potrebe testiranja definisana su 2 pacijenta
- Pacijent 1 (samhunter@gmail.com, password: password)
- Pacijent 2 (johndoe@gmail.com, password: password)
#### Student 3
- Za potrebe testiranja definisana su 2 dermatologa i 2 farmaceuta
- Dermatolog 1 (sagefinley@gmail.com, password: password)
- Dermatolog 2 (mackenzieashton@gmail.com. password: password)
  - za demonstraciju prvog prijavljivanja
- Farmaceut 1 (mickeyfrankie@gmail.com, password: password) zaposlen u "Zegin"
- Farmaceut 2 (luiswallace@gmail.com, password: password) zaposlen u "Tiny Pill"
  - za demonstraciju prvog prijavljivanja
#### Student 4
  - Za potrebe testiranja definisani su sledeci profili:
    - Adminstrator sistema
        sysadmin1@mail.com
        password
    - Nabavljac
        suppliersupplierovsky@mail.com
        password
    - Pacijent
        patient@mail.com
        password
    
