# internet-software-architecture
University project for Internet Software Architecture [2020]

[![Build Status](https://travis-ci.com/nikolapantelic-ftn/internet-software-architecture.svg?branch=main)](https://travis-ci.com/nikolapantelic-ftn/internet-software-architecture)

#### Produkciona verzija dostupna na [isa-pharmacy.herokuapp.com](https://isa-pharmacy.herokuapp.com)
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

#### Za pokretanje koristiti maven:
postaviti se u server direktorijum i izvšiti:

`$ mvn spring-boot:run`
