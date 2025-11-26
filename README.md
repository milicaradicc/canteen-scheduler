# Canteen Scheduler

Aplikacija za upravljanje rezervacijama u menzama — kreiranje menzi, radnih sati, rezervacija, provera zauzetosti, kapaciteta i slično.

## Tehnologije i verzije

- Java 17  
- Spring Boot 3.5.5  
- Spring Data JPA  
- H2 Database (in‑memory)  
- Maven  

## Kako podesiti lokalno

1. Kloniraj repozitorijum:  
   ```bash
   git clone https://github.com/milicaradicc/canteen-scheduler.git
   cd canteen-scheduler
2. Build projekta
   ```bash
   mvn clean install

4. Pokretanje aplikacije
   ```bash
   mvn spring-boot:run

Aplikacija koristi in-memory H2 bazu, tako da se podaci brišu pri svakom restartu aplikacije.

Nakon pokretanja, API je dostupan na http://localhost:8080
