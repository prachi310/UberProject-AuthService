# Auth Service

## Overview
provides JWT token-based user authentication

## Technologies Used
Spring Boot, Spring Security

##Postman Collection
https://github.com/prachi310/uber-microservices-project/blob/main/UberAuthService.postman_collection.json

## Setup
1. Clone the repository.
2. Install dependencies with `mvn install`.
4. Configure MYSQL database properties in application properties (`application.yml`).
5. Run the application with `mvn spring-boot:run`.

## Database Schema
+------------------------+--------------+------+-----+---------+----------------+
| Field                  | Type         | Null | Key | Default | Extra          |
+------------------------+--------------+------+-----+---------+----------------+
| id                     | bigint       | NO   | PRI | NULL    | auto_increment |
| created_at             | datetime     | NO   |     | NULL    |                |
| updated_at             | datetime     | NO   |     | NULL    |                |
| name                   | varchar(255) | NO   |     | NULL    |                |
| phone_number           | varchar(255) | NO   |     | NULL    |                |
| email                  | varchar(255) | NO   |     | NULL    |                |
| password               | varchar(255) | NO   |     | NULL    |                |
| active_booking_id      | bigint       | YES  | MUL | NULL    |                |
| home_id                | bigint       | YES  | MUL | NULL    |                |
| last_known_location_id | bigint       | YES  | MUL | NULL    |                |
| rating                 | double       | YES  |     | NULL    |                |
+------------------------+--------------+------+-----+---------+----------------+


