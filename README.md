# Techpulse_Assignment

# Slot Booking System — Backend Assignment

This project is a backend slot booking system built using  Spring Boot, Spring Data JPA Spring Security and H2 database. The system allows multiple users to attempt booking the same slot concurrently while guaranteeing that a slot can be booked only once

## Tech Stack
  Java 17
 Spring Boot
 Spring Data JPA
 Spring Security
 H2 Database
 Maven

---

## Locking Strategy (Pessimistic Locking)

This project uses pessimistic locking to prevent double booking.

### Implementation

When a user tries to book a slot
The slot row is locked.
Other concurrent transactions must wait for the transaction to be commit or rollback.
Only one transaction can update the slot at a time.

This makes sure that two users cannot book the same slot.


## Transaction Boundaries

The booking and cancellation operations are puted in Spring transactions using @Transactional.

### Booking Transaction

This ensures that:
Slot status update and Booking record creation happen as a single atomic unit.
If any step fails, the entire transaction rolls back.

## How Race Conditions Are Prevented

Race conditions occur when multiple users try to book the same slot at the same time.

This project prevents race conditions using:
pessimistic locking
Transaction
Slot availability checks

Flow:
First request locks the slot row
Other requests wait
After commit, waiting requests see updated status.
slot is booked → second request fails 

This ensures no double booking will happen.

Example Concurrent Booking Scenario

Assume two users attempt to book Slot #1 simultaneously.

User A
Starts transaction
Locks slot row
Marks slot as BOOKED
Creates booking
Commits transaction

User B
Waits for lock to release
Reads updated slot status
finds slot is already booked
Receives error: "Slot already booked"

Result: Only User A succeeds.

---------------------------------------------------------------


## Security

The project uses Spring Security with HTTP Basic Authentication.

Roles:
USER — can book and cancel own bookings
ADMIN — can create slots and cancel any booking

Users are stored in an simple in-memory user store for demonstration.

## Steps to Run the Application

### 1. Clone the repository

git clone <repository-url>
cd booking-system
2. Build the project
3. Run the application
4. Access H2 console

http://localhost:8080/h2-console
Use:JDBC URL: jdbc:h2:mem:testdb

## Default Credentials

### USER

username: user
password: password

### ADMIN
username: admin
password: admin


## Conclusion

This system demonstrates how to build a safe and scalable booking backend that prevents race conditions using pessimistic locking and transactions while maintaining proper API security and test coverage.
